// Tree node
class Node
{
	public int id;
	public int bossid;
	public int lvl;
	public int noofbache=0;
	public Node bache[]=new Node[1];
	public int s=1;
	public void addbache(int bacheid)
	{
		if(noofbache==s)
		{
			s=s*2;
			Node nayebache[]=new Node[s];
			for(int i=0;i<noofbache;i++)
				nayebache[i]=bache[i];
			bache=nayebache;
		}
		bache[noofbache]=new Node();
		bache[noofbache].id=bacheid;
		bache[noofbache].bossid=id;
		bache[noofbache].lvl=lvl+1;
		noofbache++;
	}
	
	public void addbache(Node node)
	{
		if(noofbache==s)
		{
			s=s*2;
			Node nayebache[]=new Node[s];
			for(int i=0;i<noofbache;i++)
				nayebache[i]=bache[i];
			bache=nayebache;
		}
		bache[noofbache]=new Node();
		bache[noofbache]=node;
		noofbache++;
	} 
	public void removebache(int bacheid)
	{
		Node nayebache[]=new Node[s];
		int i=0,y=0;
		while(i<noofbache)
		{
			if(bache[i].id!=bacheid)
			{
				nayebache[y]=bache[i];
				i++;
				y++;
			}
			else
				i++;
		}
		bache=nayebache;
		noofbache--;
	}
}


public class OrgHierarchy implements OrgHierarchyInterface{

//root node
Node root;
Avl_Node avlroot;
public int N=0;
Avl abc=new Avl();

public boolean isEmpty(){
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	if(N==0)
		return true;
	else 
		return false;	
} 

public int size(){
	//your implementation
	// throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	return N;
}

public int level(int id) throws IllegalIDException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	Avl_Node p=new Avl_Node();
	if(abc.avlroot!=null)
	{
		p=abc.search(abc.avlroot,id);
		return p.lvl;
	}
	else
	{
		throw new IllegalIDException("does not have id");
	}
} 

public void hireOwner(int id) throws NotEmptyException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	if(N==0)
	{
		root=new Node();
		root.id=id;
		root.bossid=-1;
		root.lvl=1;
		N++;
		avlroot=new Avl_Node();
		avlroot=abc.insertroot(id,-1,1);
	}
	else
	{
		throw new NotEmptyException("already have a owner");
	}
}

public void hireEmployee(int id, int bossid) throws IllegalIDException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	Avl_Node p=new Avl_Node();
	if(abc.avlroot!=null){
	p=abc.search(abc.avlroot,bossid);
	if(p!=null )
	{
		Node temp=new Node();
		temp=searchid(root,bossid);
		temp.addbache(id);
		N++;
		int lvl=p.lvl+1;
		abc.insertnode(id,bossid,lvl);
	}
	}
	else
	{
		throw new IllegalIDException("does not have id");
	}
} 

public Node searchid(Node temp,int id)
{
	if(temp.id==id)
		return temp;
	else 
	{
		int y=0;
		
		for(y=0;y<temp.noofbache;y++)
		{
			temp=searchid(temp.bache[y],id);
			if(temp!=null)
				break;
		}
		return temp;
	}
}

public void fireEmployee(int id) throws IllegalIDException{
	//your implementation
 	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
 	Avl_Node p=new Avl_Node();
 	if(abc.avlroot!=null){
	p=abc.search(abc.avlroot,id);
	if(p!=null)
	{
 		Node temp=new Node();
 		temp=searchid(root,id);
 		int t=temp.bossid;
 		temp=searchid(root,t);
 		temp.removebache(id);
 		N--;
 		abc.deletenode(abc.avlroot,id);
 	}
 	}
 	else
	{
		throw new IllegalIDException("does not have id");
	}
}
public void fireEmployee(int id, int manageid) throws IllegalIDException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	if(abc.avlroot!=null){
	Avl_Node p=new Avl_Node();
	p=abc.search(abc.avlroot,id);
	Avl_Node q=new Avl_Node();
	q=abc.search(abc.avlroot,manageid);
	abc.deletenode(abc.avlroot,id);


	Node temp1=new Node();
	Node temp2=new Node();
	Node tempboss=new Node();
	temp1=searchid(root,id);
	temp2=searchid(root,manageid);
	int bosskano=temp1.bossid;
	tempboss=searchid(root,bosskano);
	int n1=temp1.noofbache;
	int y=0;
	while(y<n1)
	{
		int l=temp1.bache[y].id;
		p=abc.search(abc.avlroot,l);
		p.bossid=manageid;
		temp2.addbache(temp1.bache[y]);
		y++;
	}
	tempboss.removebache(id);
	N--;
	}

	else
	{
		throw new IllegalIDException("does not have id");
	}
} 

public int boss(int id) throws IllegalIDException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	Avl_Node p=new Avl_Node();
	if(abc.avlroot!=null){
	p=abc.search(abc.avlroot,id);
	return p.bossid;
		}
	else
	{
		throw new IllegalIDException("does not have id");
	}
}

public int lowestCommonBoss(int id1, int id2) throws IllegalIDException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	Avl_Node p=new Avl_Node();
	if(abc.avlroot!=null){
	p=abc.search(abc.avlroot,id1);
	Avl_Node q=new Avl_Node();
	q=abc.search(abc.avlroot,id2);
	int a[]=new int[1];
	int b[]=new int[1];
	int ps=0;
	int pi=0;
	int qs=0;
	int qi=0;
	for(pi=0,ps=1;p.bossid>=-1;pi++)
	{
		if(pi==ps)
		{
			int t=2*ps;
			int arr[]=new int[t];
			for(int y=0;y<ps;y++)
			{
				arr[y]=a[y];
			}
			ps=t;
			a=arr;
		}
		a[pi]=p.bossid;
		if(a[pi]!=-1)
			p=abc.search(abc.avlroot,p.bossid);
		else
			break;
	}
	for(qi=0,qs=1;q.bossid>=-1;qi++)
	{
		if(qi==qs)
		{
			int t=2*qs;
			int brr[]=new int [t];
			for(int y=0;y<qs;y++)
			{
				brr[y]=b[y];
			}
			qs=t;
			b=brr;
		}
		b[qi]=q.bossid;
		if(b[qi]!=-1)
			q=abc.search(abc.avlroot,q.bossid);
		else
			break;
	}
	int cbi=-2;
	for(int i=0;i<pi;i++)
	{
		for(int j=0;j<qi;j++)
		{
			if(a[i]==b[j])
			{
				cbi=a[i];
				break;
				
			}
		}
		if(cbi!=-2)
		{
			break;
		}
	}
	/*
	for(int temp=0;temp<pi;temp++)
	{
		System.out.println("a="+a[temp]);
	}
	for(int temp=0;temp<qi;temp++)
	{
		System.out.println("b="+b[temp]);
	}
	*/
	
	return cbi;
}
	else
	{
		throw new IllegalIDException("does not have id");
	}
}

public String toString(int id) throws IllegalIDException{
	//your implementation
	//throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	Avl_Node poavl=new Avl_Node();
	if(abc.avlroot!=null){
	poavl=abc.search(abc.avlroot,id);
	Integer array[]=new Integer[1];
	int j=0;	// checking start
	int k=1;	// checking end
	int i=0;   	// main array ka index jahan pe abb bharenge
	int s=1;	// main array ki size
	int t=0;	// where we are checking rn
	int nt=0;
	int nj=0;
	int nk=1;
	array[i]=id;
	i++;
	while(nt<nk)
	{
		k=nk;
		String g="";
		int p=0;
		while(t<k)
		{
			g=g+abc.bosskiidwalanodesearch(abc.avlroot,array[t]);
			p=g.length();
			nt++;
			t++;
		}
				int fos=0;
				int los=0;
				int ioa=0;
				Integer a[]=new Integer[1];
				int sa=1;
				int x=0;
				while(x<p)
				{
					fos=x;
					while(g.charAt(x)!=',')
						x++;
					los=x;
					if(ioa==sa)
					{
						Integer an[]=new Integer[2*sa];
						sa=2*sa;
						for(int f=0;f<ioa;f++)
							an[f]=a[f];
						a=an;
					}
					//System.out.println("g ki substring"+g.substring(fos,los));
					a[ioa]=Integer.parseInt(g.substring(fos,los));
					ioa++;
					x++;
				}
				
				if(ioa!=0)
				{
					for(x=0;x<ioa;x++)
					{
						int m=x;
						for(int b=x+1;b<ioa;b++)
						{
							if(a[m]>a[b])
							{
								Integer o=a[b];
								a[b]=a[m];
								a[m]=o;
							}
						}
					}
				}
				
				if(p!=0)
					nj=k;
				nk=nk+ioa;
				for(x=0;x<ioa;x++)
				{
					if(i==s)
					{
						Integer arraynew[]=new Integer[2*s];
						s=2*s;
						for(int f=0;f<i;f++)
							arraynew[f]=array[f];
						array=arraynew;
					}
					array[i]=a[x];
					i++;
				}
	}
	String ans="";
	ans=String.valueOf(array[0]);
	for(int x=1;x<i;x++)
	{
		ans=ans+" "+String.valueOf(array[x]);
	}
	return ans;
}
else
	{
		throw new IllegalIDException("does not have id");
	}

}


}





























class Avl
{
	Avl_Node avlroot;
	public String bosskiidwalanodesearch(Avl_Node node, int bossid)
	{
		String a="";
		if(node.bossid==bossid)
		{
			a=a+String.valueOf(node.id)+",";
		}
		if(node.left!=null)
		{
			a=a+bosskiidwalanodesearch(node.left,bossid);
		}
		if(node.right!=null)
		{
			a=a+bosskiidwalanodesearch(node.right,bossid);
		}
		//System.out.println("in bosskiidwala string is "+a);
		return a;
	}
	public void insertnode(int id,int bossid, int lvl)
	{
		//Avl abc=new Avl();
		Avl_Node avlnode=new Avl_Node();
		avlnode.id=id;
		avlnode.bossid=bossid;
		avlnode.lvl=lvl;
		Avl_Node papa=new Avl_Node();
		papa=searchaddress(avlroot,id);
		avlnode.parent=papa;
		if(id<papa.id)
			papa.left=avlnode;
		else
			papa.right=avlnode;
		balanceinsertnode(id,papa);
	}
	public void balanceinsertnode(int id,Avl_Node node)
	{
		//System.out.println("checking balance");
		//System.out.println("node id is"+ node.id);
		int b=0;
		Avl_Node temp=new Avl_Node();
		if(node.left==null)
			b=-1-height(node.right);
		else if(node.right==null)
			b=height(node.left)+1;
		else if(node.left!=null && node.right!=null)
			b=height(node.left)-height(node.right);
		//System.out.println("b is "+ b);
		if(b>=-1 && b<=1)
		{
			temp=node.parent;
			if(temp!=null)
			{
				
				balanceinsertnode(id,temp);
			}
			else
			{
				avlroot=node;
			}
		}
		else if(b<-1)
		{
			if(id<node.right.id)
			{
				node.right=rightrotation(node.right,id);
				node=leftrotation(node,id);
			}
			else if(id>node.right.id)
			{
				node=leftrotation(node,id);
			}
		}
		else if(b>1)
		{
			if(id<node.left.id)
			{
				node=rightrotation(node,id);
			}
			else if(id>node.left.id)
			{
				node.left=leftrotation(node.left,id);
				node=rightrotation(node,id);
			}
		}

	}
	public Avl_Node leftrotation(Avl_Node node,int id)
	{
		Avl_Node y=new Avl_Node();
		Avl_Node t=new Avl_Node();
		Avl_Node papa=new Avl_Node();
		y=node.right;
		if(y.left!=null)
			t=y.left;
		else
			t=null;

		if(node.parent!=null)
			papa=node.parent;
		else
			papa=null;

		y.left=node;
		node.parent=y;
		node.right=t;
		if(t!=null)
			t.parent=node;

		if(papa!=null)
		{
			y.parent=papa;
			if(id<papa.id)
				papa.left=y;
			else
				papa.right=y;
		}
		else
		{
			y.parent=null;
			avlroot=y;
		}
		return y;
	}
	public Avl_Node rightrotation(Avl_Node node, int id)
	{
		Avl_Node y=new Avl_Node();
		Avl_Node t=new Avl_Node();
		Avl_Node papa=new Avl_Node();
		y=node.left;
		if(y.right!=null)
			t=y.right;
		else
			t=null;
		if(node.parent!=null)
			papa=node.parent;
		else
			papa=null;
		y.right=node;
		node.parent=y;
		node.left=t;
		if(t!=null)
			t.parent=node;
		if(papa!=null)
		{
			y.parent=papa;
			if(id<papa.id)
				papa.left=y;
			else
				papa.right=y;
		}
		else
		{
			y.parent=null;
			avlroot=y;
		}
		return y;
	}
	public int height(Avl_Node node)
	{
		int h=0;
		if(node.left==null && node.right==null)
		{
			//System.out.println("height m leaf node");
			h=0;
	    }
	    else if(node.left==null)
	    {
	    	//System.out.println("height m left node null");
	    	h=1+height(node.right);
	    }
	    else if(node.right==null)
	    {
	    	//System.out.println("height m right node null");
	    	h=1+height(node.left);
	    }
	    else
	    {
	    	//System.out.println("height m no node null");
	    	if(height(node.left)>=height(node.right))
	    		h=1+height(node.left);
	    	else
	    		h=1+height(node.right);
	    }
	    //System.out.println("height return");
	    return h;
	}
	public void deletenode(Avl_Node node,int id) throws IllegalIDException
	{
		//Avl abc=new Avl();
		Avl_Node temp=new Avl_Node();
		Avl_Node t1=new Avl_Node();
		Avl_Node t2=new Avl_Node();
		Avl_Node papa=new Avl_Node();
		Avl_Node mini=new Avl_Node();
		temp=search(avlroot,id);
		if(temp.parent!=null)
			papa=temp.parent;
		else
			papa=null;
		if(temp.left==null && temp.right==null)
		{
			if(id<papa.id)
				papa.left=null;
			else
				papa.right=null;
		}
		else if(temp.left==null)
		{
			t2=temp.right;
			if(id<papa.id)
			{
				papa.left=t2;
				t2.parent=papa;
			}
			else
			{
				papa.right=t2;
				t2.parent=papa;
			}
		}
		else if(temp.right==null)
		{
			t1=temp.left;
			if(id<papa.id)
			{
				papa.left=t1;
				t1.parent=papa;
			}
			else
			{
				papa.right=t1;
				t1.parent=papa;
			}
		}
		else
		{
			mini=findmini(temp.right);
			int miniid=mini.id;
			int minibossid=mini.bossid;
			int minilvl=mini.lvl;
			deletenode(avlroot,miniid);
			temp.id=miniid;
			temp.bossid=minibossid;
			temp.lvl=minilvl;
		}
		if(papa!=null)
			balancedeletenode(papa,id);
	}
	public void balancedeletenode(Avl_Node node, int id) 
	{
		int b=0;
		Avl_Node y=new Avl_Node();
		Avl_Node x=new Avl_Node();
		Avl_Node t2=new Avl_Node();
		Avl_Node t3=new Avl_Node();
		Avl_Node t4=new Avl_Node();
		Avl_Node papa=new Avl_Node();
		if(node.left==null && node.right==null)
			b=0;
		else if(node.left==null)
			b=-1-height(node.right);
		else if(node.right==null)
			b=height(node.left)+1;
		else if(node.left!=null && node.right!=null)
			b=height(node.left)-height(node.right);


		if(node.parent!=null)
			papa=node.parent;
		else
			papa=null;


		if(b>=-1 && b<=1)
		{
			if(papa!=null)
			{
				balancedeletenode(papa,id);
			}
			else
			{
				node.parent=null;
				avlroot=node;
			}
		}
		else if(b<-1)
		{
			y=node.right;
			if(y.right!=null && y.left!=null){
			if(height(y.right)>=height(y.left))
			{
				x=y.right;
				t2=y.left;

				y.left=node;
				node.parent=y;
				node.right=t2;
				t2.parent=node;
				if(papa!=null)
				{
					y.parent=papa;
					if(id<papa.id)
						papa.left=y;
					else
						papa.right=y;
					balancedeletenode(papa,id);
				}
			}
			else
			{
				x=y.left;
				t3=x.left;
				t4=x.right;

				x.left=node;
				node.parent=x;
				x.right=y;
				y.parent=x;

				node.right=t3;
				if(t3!=null)
					t3.parent=node;
				y.left=t4;
				if(t4!=null)
					t4.parent=y;

				if(papa!=null)
				{
					x.parent=papa;
					if(id<papa.id)
						papa.left=x;
					else
						papa.right=x;
					balancedeletenode(papa,id);
				}
			}}
			else if(y.right==null)
			{
				x=y.left;
				t3=x.left;
				t4=x.right;

				x.left=node;
				node.parent=x;
				x.right=y;
				y.parent=x;

				node.right=t3;
				if(t3!=null)
					t3.parent=node;
				y.left=t4;
				if(t4!=null)
					t4.parent=y;

				if(papa!=null)
				{
					x.parent=papa;
					if(id<papa.id)
						papa.left=x;
					else
						papa.right=x;
					balancedeletenode(papa,id);
				}
			}
			else if(y.left==null)
			{
				x=y.right;
				//t2=y.left;

				y.left=node;
				node.parent=y;
				node.right=null;
				//t2.parent=node;
				if(papa!=null)
				{
					y.parent=papa;
					if(id<papa.id)
						papa.left=y;
					else
						papa.right=y;
					balancedeletenode(papa,id);
				}
			}
		}
		else if(b>1)
		{
			y=node.left;
			if(y.left!=null && y.right!=null){
			if(height(y.left)>=height(y.right))
			{
				x=y.left;
				t2=y.right;

				y.right=node;
				node.parent=y;
				node.left=t2;
				t2.parent=node;
				if(papa!=null)
				{
					y.parent=papa;
					if(id<papa.id)
						papa.left=y;
					else
						papa.right=y;
					balancedeletenode(papa,id);
				}
			}
			else
			{
				x=y.right;
				t3=x.left;
				t4=x.right;

				x.right=node;
				node.parent=x;
				x.left=y;
				y.parent=x;

				node.left=t4;
				if(t4!=null)
					t4.parent=node;
				y.right=t3;
				if(t3!=null)
					t3.parent=y;

				if(papa!=null)
				{
					x.parent=papa;
					if(id<papa.id)
						papa.left=x;
					else
						papa.right=x;
					balancedeletenode(papa,id);
				}
			}}
			else if(y.right==null)
			{
				x=y.left;
				//t2=y.right;

				y.right=node;
				node.parent=y;
				node.left=null;
				//t2.parent=node;
				if(papa!=null)
				{
					y.parent=papa;
					if(id<papa.id)
						papa.left=y;
					else
						papa.right=y;
					balancedeletenode(papa,id);
				}
			}
			else if(y.left==null)
			{
				x=y.right;
				t3=x.left;
				t4=x.right;

				x.right=node;
				node.parent=x;
				x.left=y;
				y.parent=x;

				node.left=t4;
				if(t4!=null)
					t4.parent=node;
				y.right=t3;
				if(t3!=null)
					t3.parent=y;

				if(papa!=null)
				{
					x.parent=papa;
					if(id<papa.id)
						papa.left=x;
					else
						papa.right=x;
					balancedeletenode(papa,id);
				}
			}
		}
	}

	public Avl_Node findmini(Avl_Node node)
	{
		if(node.left==null)
			return node;
		return findmini(node.left);
	}
	public Avl_Node search(Avl_Node node,int id) throws IllegalIDException
	{
		Avl_Node temp=new Avl_Node();
		if(node.id==id)
			return node;
		else if(id<node.id)
		{
			if(node.left!=null)
			{
				temp=search(node.left,id);
			}
			else
				throw new IllegalIDException("does not exist");
		}
		else if(id>node.id)
		{
			//System.out.println("reached here for finding the boss");
			if(node.right!=null)
			{
				//System.out.println("going to get");
				temp=search(node.right,id);
				//System.out.println("got");
			}
			else
				throw new IllegalIDException("does not exist");
		}
		//System.out.println("going to return");
		return temp;
	}
	public Avl_Node insertroot(int id,int bossid, int lvl)
	{
		avlroot=new Avl_Node();
		avlroot.id=id;
		avlroot.bossid=bossid;
		avlroot.lvl=lvl;
		return avlroot;
	}

	public Avl_Node searchaddress(Avl_Node node, int bachaid)
	{
		
		if(bachaid<node.id)
		{
			//System.out.println("if m aarha h papasearch");
			if(node.left!=null)
			{
				//System.out.println("if ke baad if m aarha h papasearch");
				return searchaddress(node.left,bachaid);
			}
			else
			{
				//System.out.println("if ke baad else m aarha h papasearch");
				return node;
			}
		}
		else
		{
			//System.out.println("else m aarha h papasearch");
			if(node.right!=null)
			{
				//System.out.println("else ke baad if m aarha h papasearch");
				return searchaddress(node.right,bachaid);
			}
			else
			{
				//System.out.println("else ke baad else m aarha h papasearch");
				return node;
			}
		}
	}
	public String preorder(Avl_Node node)
	{
		String a=" ";
		if(node.left!=null)
			a=a+preorder(node.left)+" ";
		a=a+String.valueOf(node.id)+" ";
		if(node.right!=null)
			a=a+preorder(node.right)+" ";
		return a;
	}
	/*
	public static void main(String[] args)
	{
		String a="";
		String b="";
		String c="";
		String d="";
		String k="";
		String f="";
		String g="";
		Avl_Node x=new Avl_Node();
		Avl abc=new Avl();
		abc.insertroot(8,-1,1);
		a=abc.preorder(abc.avlroot);
		System.out.println("a = "+a);
		abc.insertnode(6,1,2);
		b=abc.preorder(abc.avlroot);
		System.out.println("b = "+b);
		abc.insertnode(7,1,2);
		c=abc.preorder(abc.avlroot);
		System.out.println("c = "+c);
		System.out.println("left node id "+abc.avlroot.left.id);
		abc.insertnode(4,1,2);
		d=abc.preorder(abc.avlroot);
		System.out.println("d = "+d);
		abc.insertnode(3,2,3);
		abc.insertnode(2,4,3);
		abc.insertnode(1,3,3);
		k=abc.preorder(abc.avlroot);
		System.out.println("k = "+k);


		try
		{
			x=abc.search(abc.avlroot, 5);
			System.out.println("x ke id = "+x.id);
			System.out.println("x ke bossid = "+x.bossid);
			System.out.println("x ke lvl = "+x.lvl);
		}
		catch(IllegalIDException e)
		{
      		System.out.println(e);
     	}

		
		try
		{
			abc.deletenode(abc.avlroot,5);
			f=abc.preorder(abc.avlroot);
			System.out.println("f = "+f);
		}
		catch(IllegalIDException e)
		{
      		System.out.println(e);
     	}
     	try
		{
			abc.deletenode(abc.avlroot,4);
			g=abc.preorder(abc.avlroot);
			System.out.println("g = "+g);
		}
		catch(IllegalIDException e)
		{
      		System.out.println(e);
     	}
    
	} */
}

class Avl_Node
{
	public int id;
	public int bossid;
	public int lvl;
	public Avl_Node parent;
	public Avl_Node left;
	public Avl_Node right;
	public Avl_Node()
	{
		left=null;
		right=null;
		parent=null;
	}
}

