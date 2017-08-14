package org.blue.blacklist.action;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import org.blue.blacklist.bean.Update;
import org.blue.blacklist.bean.User;
import org.blue.blacklist.utils.HttpClientUtils;
import org.blue.blacklist.view.MainWindow;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



public class ZhihuActionImpl extends HttpClientUtils implements ZhihuAction{
	private String apiURI = "";
	
	@Override
	public String findCurrentName(String cookie) {
		return getZhihuName(cookie);
	}

	@Override
	public void updateBlackList(Vector vectorData) {

		String jsonStr = "";
		try {
			jsonStr = get(apiURI+"?action=list");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		JSONArray jsonarray = JSONArray.fromObject(jsonStr);
		
		List<User> userList = (List<User>) JSONArray.toCollection(jsonarray, User.class);
		
		for (User user : userList) {
			Vector vector = new Vector();
			vector.addElement(user.getId());
			vector.addElement(user.getNickname());
			vector.addElement(user.getZhihuname());
			vector.addElement(user.getDescription());
			vector.addElement(user.getBlackcount());
			
			vectorData.addElement(vector);
		}
		
		
	
	}


	@Override
	public boolean pullToBlackList(String zhihuName , Object id , String string) {
		try {
			String api = apiURI+"?action=count&id="+id;
			get(api);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  pullToBlackListByName(zhihuName, string);
		
	}

	@Override
	public void updateBlackedList(DefaultListModel blackedList, String cookie) {
		
		List<String> findBlackList = findBlackList(cookie);
		for (String name : findBlackList) {
			blackedList.addElement(name);
		}
		
	}

	@Override
	public URI getUserDefaultPageById(String idValue) throws URISyntaxException {
		String url = "https://www.zhihu.com/people/"+idValue;
		return new URI(url);
	}

	@Override
	public boolean followAuthorAccount(String cookie) {
		return followAuthor(cookie);
	}

	@Override
	public void checkUpdate(Desktop desktop) {

		String json = null;
		try {
			json = get(apiURI+"?action=update");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JSONObject fromObject = JSONObject.fromObject(json);
		Update update = (Update) fromObject.toBean(fromObject, Update.class);
		
		if(!(MainWindow.VERSION.equals(update.getVersion()))){
			int s = JOptionPane.showConfirmDialog(null, "有新版本，是否更新?");
			if(s==0){
			try {
				desktop.browse(new URI(update.getUrl()));
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			}
		}
		
	}



	

}
