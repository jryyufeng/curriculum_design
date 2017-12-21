//查看文件类型
package Look;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.util.Stack;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeExpansionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import Shuju.Queue;
public class Looktree<T> extends Lookone implements ActionListener{
	private JTree tree;
	public void actionPerformed(ActionEvent e){
		Looktree f1=new Looktree();
		f1.Looktree1();
	}
	//树的结构，用于转化
	public class Node<Tree> {
		private Tree data;
		private int parent;
		
		public Node(){
		}
		
		public Node(Tree data){
			this.data = data;
		}
		
		public Node(Tree data,int parent){
			this.data = data;
			this.parent = parent;
		}
		
		public void setData(Tree data){
			this.data = data;
		}
		
		public Tree getData(){
			return this.data;
		}
		
		public void setParent(int parent){
			this.parent = parent;
		}
		
		public int getParent(){
			return this.parent;
		}
	}

	
	public void Looktree1(){
		JFrame f1=new JFrame();
		 Container c=f1.getContentPane();
		f1.setTitle("用户文件信息");
		f1.setSize(500,360);
		//f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
		tree=new JTree();
		tree.addTreeExpansionListener(new TreeExpansionAction());
		setRootDir(new File("d:/XM/"+s1));
		String imgePath1="d:/XM/putice/yh1.jpg";
		JPanel p5;//面板
		final Image img=Toolkit.getDefaultToolkit().createImage(imgePath1);
		p5=new JPanel(){
			protected void paintChildren(Graphics g){
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};//?
		//f1.add(p5);
		JScrollPane p6=new JScrollPane(tree){
			protected void paintChildren(Graphics g){
				g.drawImage(img, 0, 0, this);
				super.paintChildren(g);
			}
		};
		f1.add(p6);
		tree.setOpaque(false);
		p6.setOpaque(false);
		p6.getViewport().setOpaque(false);
		
		f1.setVisible(true);
	}
	private void setRootDir(File dir){
		tree.setModel(new DefaultTreeModel(createNode(dir)));
		MutableTreeNode rootNode=(MutableTreeNode)tree.getModel().getRoot();//根节点
		//rootNode.setUserObject(dir);//显示根目录路径
		updateNode(rootNode);
		tree.updateUI();
		
		
	}
	private void updateNode(Object object){
		DefaultMutableTreeNode node=(DefaultMutableTreeNode)object;
		for(int i=0;i<node.getChildCount();i++){
			DefaultMutableTreeNode node2=(DefaultMutableTreeNode)node.getChildAt(i);
			FileObject fileObject=(FileObject)node2.getUserObject();
			if(!fileObject.isUpdated()){
				fileObject.setUpdated(true);
				File file=fileObject.getFile();
				if(file.isDirectory()){
					addSubFile(node2,file);
				}
			}
		}
	}
	public MutableTreeNode createNode(File dir){
		DefaultMutableTreeNode node=new DefaultMutableTreeNode(new FileObject(dir));
		if(dir.isDirectory()){
			addSubFile(node,dir);
		}
		return node;
	}
	private void addSubFile(DefaultMutableTreeNode node,File dir){
		File[] files=dir.listFiles();
		if(files!=null){
			for(File file:files){
				node.add(new DefaultMutableTreeNode(new FileObject(file)));
			}
		}
			
	}
	public class TreeExpansionAction implements TreeExpansionListener{

		public void treeCollapsed(TreeExpansionEvent event) {
			// TODO Auto-generated method stub
		}

		public void treeExpanded(TreeExpansionEvent event) {
			// TODO Auto-generated method stub
			updateNode(event.getPath().getLastPathComponent());
		}
		
	}
	
	public class FileObject{
		private File file;
		private boolean updated;
		
		public FileObject(File file){
			this.file=file;
		}
		//得到文件路径
		public File getFile(){
			return file;
		}
		//
		public boolean isUpdated(){
			return updated;
		}
		//
		public void setUpdated(boolean updated){
			this.updated=updated;
			
		}
		//将节点信息变为字符串
		public String toString(){
			return file.getName();
		}
	}
	
}
