package org.blue.blacklist.action;

import java.awt.Desktop;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.DefaultListModel;

public interface ZhihuAction {

	String findCurrentName(String cookie);

	void updateBlackList(Vector vectorData);

	boolean pullToBlackList(String zhihuName, Object id, String string);

	void updateBlackedList(DefaultListModel blackedList, String text);

	URI getUserDefaultPageById(String idValue) throws URISyntaxException;

	boolean followAuthorAccount(String cookie);

	void checkUpdate(Desktop desktop);



}
