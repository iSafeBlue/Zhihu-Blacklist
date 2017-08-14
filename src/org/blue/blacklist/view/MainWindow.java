package org.blue.blacklist.view;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Vector;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.table.TableModel;

import org.apache.commons.io.FileUtils;
import org.blue.blacklist.action.ZhihuAction;
import org.blue.blacklist.action.ZhihuActionImpl;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class MainWindow {
	
	public static final String VERSION = "1.2";
	private JFrame frame;
	private JTable table;
	private ZhihuAction zhihu = new ZhihuActionImpl();
	private Desktop desktop = Desktop.getDesktop();
	static{
		  try
		    {
		        org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		        UIManager.put("RootPane.setupButtonVisible", false);
		    }
		    catch(Exception e)
		    {
		    }
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
		update();
	}

	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(MainWindow.class.getResource("/org/blue/blacklist/logo.png")));
		frame.setTitle("\u77E5\u4E4E\u9ED1\u540D\u5355\u7CFB\u7EDF");
		frame.setBounds(100, 100, 568, 415);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		final Vector  vectorData = new Vector();

		Vector vectorHeader = new Vector();
		vectorHeader.addElement("ID");
		vectorHeader.addElement("ÖªºõêÇ³Æ");
		vectorHeader.addElement("ÖªºõID");
		vectorHeader.addElement("ÃèÊö");
		vectorHeader.addElement("À­ºÚ´ÎÊý");
		DefaultTableModel defaultTableModel = new DefaultTableModel(vectorData,vectorHeader);
		
		
		
		frame.getContentPane().setLayout(null);
				 final DefaultListModel blackedList = new DefaultListModel();
				/*list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION); 
				list.setSelectedIndex(0);
				list.setVisibleRowCount(3);    
				*/
				
				JPanel bg = new JPanel();
				bg.setBounds(0, 0, 552, 377);
				
				bg.setLayout(new BorderLayout(0, 0));
				
				JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
				bg.add(tabbedPane);
				
				JPanel panel_1 = new JPanel();
				tabbedPane.addTab(" ºÚÃûµ¥ ", null, panel_1, null);
				panel_1.setLayout(null);
				
				JPanel panel_3 = new JPanel();
				panel_3.setBounds(19, 59, 489, 211);
				panel_1.add(panel_3);
				panel_3.setLayout(new BorderLayout(0, 0));
				
				
				
				table = new JTable(defaultTableModel){
					@Override
					public boolean isCellEditable(int row, int column) {
					     return false;
					}
					
				};
				TableColumn column1 = table.getColumnModel().getColumn(0);
				column1.setPreferredWidth(40);
				column1.setMaxWidth(50);
				table.addMouseListener(new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {

						if(e.getClickCount() == 2){
							int row = ((JTable)e.getSource()).rowAtPoint(e.getPoint());
							String idValue = (String) table.getValueAt(row, 2);
							
							try {
								URI thisUserURI = zhihu.getUserDefaultPageById(idValue);
								desktop.browse(thisUserURI);
							} catch (IOException e1) {
								e1.printStackTrace();
							} catch (URISyntaxException e1) {
								e1.printStackTrace();
							}
							
						}
					}
				});
				
				
						JScrollPane scrollPane = new JScrollPane(table);
						panel_3.add(scrollPane);
						
						
						
						final JLabel accountLabel = new JLabel("\u672A\u767B\u5F55");
						accountLabel.setFont(new Font("Î¢ÈíÑÅºÚ", Font.BOLD, 14));
						accountLabel.setBounds(60, 18, 81, 15);
						panel_1.add(accountLabel);
						
						JButton btnUpdate = new JButton("\u66F4\u65B0\u9ED1\u540D\u5355");
						
						btnUpdate.setBounds(414, 10, 81, 31);
						panel_1.add(btnUpdate);
						
						final JButton btnPullBlack = new JButton("\u62C9\u9ED1\u9009\u4E2D");
						btnPullBlack.setEnabled(false);
						
						btnPullBlack.setBounds(323, 10, 81, 31);
						panel_1.add(btnPullBlack);
						
						final JProgressBar progressBar = new JProgressBar();
						progressBar.setBounds(159, 10, 154, 30);
						panel_1.add(progressBar);
						
						JLabel label = new JLabel("\u72B6\u6001\uFF1A");
						label.setFont(new Font("ËÎÌå", Font.PLAIN, 14));
						label.setBounds(19, 18, 54, 15);
						panel_1.add(label);
						
						JPanel panel = new JPanel();
						tabbedPane.addTab("µÇÂ¼Öªºõ", null, panel, null);
						panel.setLayout(null);
						JLabel lblCookie = new JLabel("\u5728\u4E0B\u65B9\u6587\u672C\u57DF\u8F93\u5165\u60A8\u77E5\u4E4E\u8D26\u53F7\u7684Cookie\u540E\u70B9\u51FB\"\u767B\u5F55\"\u5373\u53EF\u5237\u65B0\u72B6\u6001");
						lblCookie.setBounds(68, 10, 406, 15);
						panel.add(lblCookie);
						
						JPanel panel_4 = new JPanel();
						panel_4.setBounds(10, 35, 479, 215);
						panel.add(panel_4);
						panel_4.setLayout(new BorderLayout(0, 0));
						
						JScrollPane scrollPane_1 = new JScrollPane();
						panel_4.add(scrollPane_1);
						
						final JTextArea cookieText = new JTextArea();
						scrollPane_1.setViewportView(cookieText);
						cookieText.setLineWrap(true);
						
						
						
						//¶Ácookie
						
						new Thread(){
							public void run() {
								File file = new File("cookie.txt");
								
								if(file.exists())
								{
									String readFileCookie = "";
									try {
									readFileCookie = FileUtils.readFileToString(file);
									} catch (IOException e) {
										e.printStackTrace();
									}
									cookieText.setText(readFileCookie);
								}
								
							};
						}.start();
						
						//
						
						final JButton btnUpdateBkl = new JButton("\u5237\u65B0\u9ED1\u540D\u5355");
						
								JButton btnRefresh = new JButton("\u767B\u5F55");
								btnRefresh.setBounds(210, 256, 72, 29);
								panel.add(btnRefresh);
								
								final JCheckBox checkBox = new JCheckBox("\u8BB0\u4F4F\u767B\u5F55\u72B6\u6001");
								checkBox.setSelected(true);
								checkBox.setBounds(68, 259, 136, 23);
								panel.add(checkBox);
								
								JButton btnNewButton = new JButton("\u7C98\u8D34");
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										cookieText.paste();
									}
								});
								btnNewButton.setBounds(311, 256, 72, 29);
								panel.add(btnNewButton);
								btnRefresh.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										
										
										
										new Thread(new Runnable() {
											public void run() {
												String cookie = cookieText.getText();
												File fileCookie = new File("cookie.txt");
												if(checkBox.isSelected()){
													if(!fileCookie.exists()){
														try {
															FileUtils.writeStringToFile(fileCookie, cookie);
														} catch (IOException e) {
															e.printStackTrace();
														}
													}
												}
												
												
												String name = zhihu.findCurrentName(cookie);
												if("".equals(name) || name == null || "¶þÎ¬ÂëµÇÂ¼".equals(name)){
													JOptionPane.showMessageDialog(null, "µÇÂ¼Òì³££¬ÇëÖØÊÔ»ò¼ì²éCookieÊÇ·ñÕýÈ·", "ÌáÊ¾", JOptionPane.ERROR_MESSAGE);
												}else{
													JOptionPane.showMessageDialog(null, "»¶Ó­£º"+name);
													accountLabel.setText(name);
													btnPullBlack.setEnabled(true);
													btnUpdateBkl.setEnabled(true);
												}
											}
										}).start();
										
									}
								});
								
										
								btnUpdate.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												
												new Thread(){
													@Override
													public void run() {
														vectorData.removeAllElements();
														
														zhihu.updateBlackList(vectorData);
														
														table.updateUI();
													}
												}.start();
												
											}
								});
								
				btnPullBlack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
								
								new Thread(){
									@Override
									public void run() {
										progressBar.setValue(0);
										
										int selectedRowCount = table.getSelectedRowCount();
										
										int state = JOptionPane.showConfirmDialog(null, "ÄúÒÑÑ¡ÖÐ"+selectedRowCount+"¸öÈË£¬ÊÇ·ñ½«ËûÃÇÁÐÈëÄúµÄºÚÃûµ¥");
										int[] selectedRows = table.getSelectedRows();
										
										int step = (int) Math.ceil(((double)progressBar.getMaximum() / selectedRowCount));
										if(state==0){
										for (int row : selectedRows) {
											
											
											progressBar.setValue(progressBar.getValue()+step);
											
											String zhihuName = (String) table.getValueAt(row, 2);
											Object id = table.getValueAt(row, 0);
											//System.out.println(id);
											boolean status = zhihu.pullToBlackList(zhihuName , id , cookieText.getText());
											//System.out.println(status);
											
											try {
												Thread.currentThread().sleep(1000);
											} catch (InterruptedException e) {
												e.printStackTrace();
											}
											
										}
										}
										
									}
								}.start();
								
								
					}
				});
				
								
								JPanel panel_2 = new JPanel();
								tabbedPane.addTab(" ÒÑÀ­ºÚ ", null, panel_2, null);
								panel_2.setLayout(null);
								
								JPanel panel_5 = new JPanel();
								panel_5.setBounds(139, 45, 216, 252);
								panel_2.add(panel_5);
								panel_5.setLayout(new BorderLayout(0, 0));
								
								JScrollPane scrollPane_2 = new JScrollPane();
								
								
								final JList list = new JList(blackedList);
								
												scrollPane_2.setViewportView(list);
												panel_5.add(scrollPane_2);
												
												
												btnUpdateBkl.setEnabled(false);
												btnUpdateBkl.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent e) {
														
														new Thread(){
															
															public void run() {
																
																blackedList.removeAllElements();
																
																zhihu.updateBlackedList(blackedList , cookieText.getText());
																
																list.updateUI();
															};
															
														}.start();
														
													}
												});
												btnUpdateBkl.setBounds(180, 2, 135, 33);
												panel_2.add(btnUpdateBkl);
												
				JPanel panel_6 = new JPanel();
				tabbedPane.addTab("  ¹ØÓÚ  ", null, panel_6, null);
				panel_6.setLayout(null);
				
				final JLabel zhihuLogoLabel = new JLabel();
				zhihuLogoLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/org/blue/blacklist/logo.png")));
				zhihuLogoLabel.setBounds(10, 27, 200, 200);
				panel_6.add(zhihuLogoLabel);
				
				JLabel label_1 = new JLabel("\u77E5\u4E4E\uFF1A");
				label_1.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				label_1.setBounds(234, 27, 62, 35);
				panel_6.add(label_1);
				
				JLabel label_2 = new JLabel("@\u6D45\u84DD");
				label_2.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				label_2.setBounds(293, 27, 74, 34);
				panel_6.add(label_2);
				
				JLabel followerLable = new JLabel();
				ImageIcon imageIcon = new ImageIcon(MainWindow.class.getResource("/org/blue/blacklist/follower.jpg"));
				followerLable.setIcon(imageIcon);
				followerLable.setBounds(364, 27, imageIcon.getIconWidth(), imageIcon.getIconHeight());
				followerLable.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				followerLable.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						if(!(accountLabel.getText().equals("Î´µÇÂ¼")) && zhihu.followAuthorAccount(cookieText.getText())){
							JOptionPane.showMessageDialog(null, "ÒÑ¹Ø×¢");
						}
						
					}
					
				});
				
				panel_6.add(followerLable);
				
				JLabel label_3 = new JLabel("\u5FAE\u4FE1\uFF1A");
				label_3.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				label_3.setBounds(234, 86, 62, 35);
				panel_6.add(label_3);
				
				JLabel label_4 = new JLabel("@\u6D45\u84DD");
				label_4.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				label_4.setBounds(293, 87, 74, 34);
				label_4.setCursor(new Cursor(Cursor.HAND_CURSOR));
				
				label_4.addMouseListener( new MouseAdapter() {

					@Override
					public void mouseClicked(MouseEvent e) {
						zhihuLogoLabel.setIcon(new ImageIcon(MainWindow.class.getResource("/org/blue/blacklist/wechat.png")));
						zhihuLogoLabel.updateUI();
					}
					
				});
				
				panel_6.add(label_4);
				
				JLabel lbllxghost = new JLabel("\u63D0\u4EA4\u9ED1\u540D\u5355\u53EF\u79C1\u4FE1\u6216@");
				lbllxghost.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				lbllxghost.setBounds(234, 143, 242, 35);
				panel_6.add(lbllxghost);
				
				JLabel label_6 = new JLabel("\u6D45\u84DD");
				label_6.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				label_6.setBounds(390, 192, 62, 35);
				label_6.setCursor(new Cursor(Cursor.HAND_CURSOR));
				label_6.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							desktop.browse(new URI("https://www.zhihu.com/people/isafe_blue"));
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
					
				});
				panel_6.add(label_6);
				
				JLabel lblLxghost = new JLabel("lxghost");
				lblLxghost.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				lblLxghost.setBounds(234, 192, 74, 35);
				lblLxghost.setCursor(new Cursor(Cursor.HAND_CURSOR));
				lblLxghost.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							desktop.browse(new URI("https://www.zhihu.com/people/lxghost"));
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
					
				});
				panel_6.add(lblLxghost);
				
				JLabel label_5 = new JLabel("\u9B4F\u5341\u4E03");
				label_5.setFont(new Font("Î¢ÈíÑÅºÚ", Font.PLAIN, 20));
				label_5.setBounds(318, 192, 62, 35);
				label_5.setCursor(new Cursor(Cursor.HAND_CURSOR));
				label_5.addMouseListener( new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						try {
							desktop.browse(new URI("https://www.zhihu.com/people/weishiqi"));
						} catch (IOException e1) {
							e1.printStackTrace();
						} catch (URISyntaxException e1) {
							e1.printStackTrace();
						}
					}
					
				});
				panel_6.add(label_5);
				

		
				frame.getContentPane().add(bg);
	}
	private void update() {
		
		new Thread(){
			@Override
			public void run() {
				zhihu.checkUpdate(desktop);
			}
		}.start();
		
	}
}
