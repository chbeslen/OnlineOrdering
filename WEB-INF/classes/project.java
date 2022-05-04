import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
public class project extends HttpServlet{
	ArrayList <menuItem> cart = new ArrayList<menuItem>();
	int itemSelected = 0;
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		doPost(req, res);  
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("text/html");
		PrintWriter  msg = res.getWriter();
		String menuItemsPage = "";
		menu m = new menu();
		if(req.getParameter("OnlineOrder").compareTo("OnlineOrder") == 0 || req.getParameter("OnlineOrder").compareTo("Back") == 0){
			menuItemsPage+=menuPage(req,menuItemsPage,m);
		}
		else if(req.getParameter("OnlineOrder").compareTo("Add to Cart") == 0){
			menuItemsPage+=addToCart(req,menuItemsPage,m);
		}
		else if(req.getParameter("OnlineOrder").compareTo("Continue") == 0){
			menuItemsPage+=menuPage(req,menuItemsPage,m);
		}
		else if(req.getParameter("OnlineOrder").compareTo("Complete Order") == 0){
			menuItemsPage+=checkOut(req,menuItemsPage,m);
		}
		else if(req.getParameter("OnlineOrder").compareTo("CheckOut") == 0){
			menuItemsPage+=receipt(req,menuItemsPage,m);
		}
		else{
			menuItemsPage+=itemPage(req,menuItemsPage,m);
		}
		msg.println(menuItemsPage);

	} // doPost
	String menuPage(HttpServletRequest req,String menuItemsPage, menu m){
		menuItemsPage += "\n<html>\n\t <head>\n\t <title> CMP663 Restaurant Online Ordering Menu </title>\n<link rel='stylesheet' type='text/css' href='project.css'/>\n<script type='text/javascript' src='project.js'>\n\t</head> \n</script>";
		menuItemsPage += "\n<body class='home'>\n\t<form id='menu' method='post' action='http://localhost:9091/project/orderOnline'>\n\t\t<center>\n\t\t\t<img id='img1' title='logo' src='logo.png'>";
		String type = "";
		for(int i = 0; i < m.menuItems.size(); i++){
			if(m.menuItems.get(i).getType().compareTo(type) != 0){
				type = m.menuItems.get(i).getType();
				menuItemsPage += "\n\t\t\t<h1>" + type + "</h1><br>";
			}
			menuItemsPage += "\n\t\t\t<input type='submit' name='OnlineOrder' value='" + m.menuItems.get(i).getName() + "'/>";
			if(i % 6 == 1)
				menuItemsPage += "\n<br><br>";
		}
		menuItemsPage += "\n</form></body></html>";
		return menuItemsPage;
	}
	String addToCart(HttpServletRequest req,String menuItemsPage, menu m){
		cart.add(m.menuItems.get(itemSelected)); 
		menuItemsPage += "\n<html>\n\t <head>\n\t <title> CMP663 Restaurant Cart </title>\n<link rel='stylesheet' type='text/css' href='project.css'/>\n<script type='text/javascript' src='project.js'>\n\t</head> \n</script>";
		menuItemsPage += "\n<body>\n\t<form id='menu' method='post' action='http://localhost:9091/project/orderOnline'>\n\t\t<center>\n\t\t\t<img id='img1' title='logo' src='logo.png'>";
		menuItemsPage += "\n\t\t\t<h1> Shopping Cart </h1><br>"; 
		for(int i = 0; i < cart.size(); i++){
				menuItemsPage += "\n\t\t\t<a name='orderType' value='"+ cart.get(i).getType() +"'><strong>Dish Type:</strong>\t " + cart.get(i).getType() + "</a>\n\t\t\t";
				menuItemsPage += "\n\t\t\t<a name='ordername' value='"+ cart.get(i).getName() +"'><strong>Dish Name:</strong>\t " + cart.get(i).getName() + "</a>\n\t\t\t";
				menuItemsPage += "\n\t\t\t<a name='orderPrice' value'="+ cart.get(i).getPrice() +"'><strong>Dish Price:</strong>\t $" + cart.get(i).getPrice() + "</a><br>\n\t\t\t";
				menuItemsPage += "\n\t\t\t<a name='orderDisc' value='"+ cart.get(i).getDiscription() +"'><strong>Dish Discription:</strong>\t " + cart.get(i).getDiscription() + "</a><br>\n\t\t\t";

		}
		menuItemsPage += "<br>\n\t\t\t<input type='submit' name='OnlineOrder' value='Complete Order'/>";
		menuItemsPage += "\n\t\t\t<input type='submit' name='OnlineOrder' value='Continue'/>";
		menuItemsPage += "\n</form></body></html>";
		return menuItemsPage;
	}
	String itemPage(HttpServletRequest req,String menuItemsPage, menu m){
		menuItemsPage += "\n<html>\n\t <head>\n\t <title> CMP663 Restaurant " + req.getParameter("OnlineOrder") +"</title>\n\t</head>";
		menuItemsPage += "\n<body class='home' >\n\t<form id='menu' method='post' action='http://localhost:9091/project/orderOnline'>\n\t\t<center>\n\t\t\t<img id='img1' title='logo' src='logo.png'>";
		menuItemsPage += "\n\t\t\t<h1> Menu Item Page </h1><br>";
		for(int i = 0; i < m.menuItems.size(); i++){
			if(m.menuItems.get(i).getName().compareTo(req.getParameter("OnlineOrder")) == 0){ 
				menuItemsPage += "<br>\n\t\t\t<a name='orderType' value="+ m.menuItems.get(i).getType() +"><strong>Dish Type:</strong>\t " + m.menuItems.get(i).getType() + " " + "</a>\n\t\t\t";
				menuItemsPage += "\n\t\t\t<a name='orderName' value="+ m.menuItems.get(i).getName() +"><strong>Dish Name:</strong>\t " + m.menuItems.get(i).getName() + " " + "</a>\n\t\t\t";
				menuItemsPage += "\n\t\t\t<a name='orderPrice' value="+ m.menuItems.get(i).getPrice() +"><strong>Dish Price:</strong>\t $" + m.menuItems.get(i).getPrice() + " " +"</a><br>\n\t\t\t";
				menuItemsPage += "\n\t\t\t<a name='orderDisc' value="+ m.menuItems.get(i).getDiscription() +"><strong>Dish Discription:</strong>\t " + m.menuItems.get(i).getDiscription() + "</a><br>\n\t\t\t";
				itemSelected = i;
			}
		}
		menuItemsPage += "<br>\n\t\t\t<input type='submit' name='OnlineOrder' value='Add to Cart'/>";
		menuItemsPage += "\n\t\t\t<input type='submit' name='OnlineOrder' value='Back'/>";
		menuItemsPage += "\n</form></body></html>";
		return menuItemsPage;
	}
	String checkOut(HttpServletRequest req,String menuItemsPage, menu m){
		menuItemsPage += "\n<html>\n\t <head>\n\t <title> CMP663 Restaurant Check Out </title>\n\t <script type='text/javascript' src='final.js'></script></head>";
		menuItemsPage += "\n<body>\n\t<form id='menu' method='post' action='http://localhost:9091/project/orderOnline' onsubmit='return CheckandSubmit()'>\n\t\t<center>\n\t\t\t<img id='img1' title='logo' src='logo.png'>";
		menuItemsPage += "\n\t\t\t<h1>Pickup Order Check Out</h1><br>";
		menuItemsPage += "\n<div id='infoDiv'>";
		menuItemsPage += "\nFirst Name: <input type='text' name='firstName' id='firstName' size='8'/>";
		menuItemsPage += "\nLast Name: <input type='text' name='lastName' id='lastName' size='8'/><br> <br>";
		menuItemsPage += "\nPhone Numer: <input type='text' name='Phone' id='Phone' size='10'/> ";
		menuItemsPage += "\nE-Mail Address: <input type='text' name='email' id='email' size='20'/>  <br> <br>";
		menuItemsPage += "\nAddress: <input type='text' name='Address' id='Address' size='15'/>";
		menuItemsPage += "\nCity: <input type='text' name='City' id='City' size='8'/>";
		menuItemsPage += "\nState: <input type='text' name='state' id='state' size='1'/>";
		menuItemsPage += "\nZip Code: <input type='text' name='Zip' id='Zip' size='3'/> \n</div><br><br>";
		for(int i = 0; i < cart.size(); i++){
			menuItemsPage += "<a name='orderType' value='"+ cart.get(i).getType() +"' style='margin-left: 10px'><strong>Dish Type:</strong>\t " + cart.get(i).getType()  + "\n\t\t\t";
			menuItemsPage += "<a name='ordername' value='"+ cart.get(i).getName() +"' style='margin-left: 10px'><strong>Dish Name:</strong>\t " + cart.get(i).getName() + "\n\t\t\t";
			menuItemsPage += "\n\t\t\t<a name='orderPrice' value'="+ cart.get(i).getPrice() +"'><strong>Dish Price:</strong>\t $" + cart.get(i).getPrice() + "<br>\n\t\t\t";
		}
		menuItemsPage += "<br>\n\t\t\t<input type='submit' name='OnlineOrder' value='CheckOut'/>";
		menuItemsPage += "\n</form></body></html>";
		return menuItemsPage;
	}
	String receipt(HttpServletRequest req,String menuItemsPage, menu m){
		double subtotal = 0;
		menuItemsPage += "\n<html>\n\t <head>\n\t <title> CMP663 Restaurant Check Out </title>\n\t</head>";
		menuItemsPage += "\n<body>\n\t<form id='menu' method='post' action='http://localhost:9091/project/'>\n\t\t<center>\n\t\t\t";
		menuItemsPage += "\n\t\t\t<h3>Receipt<br>";
		menuItemsPage += "\nCMP663 Restaurant<br>4401 Village Dr,<br> Fairfax, VA 22030<br> +1 (800) 514-6848</h3><br>";
		menuItemsPage += "===================================<br>";
		menuItemsPage += req.getParameter("firstName") + " " + req.getParameter("lastName") + "<br>" + req.getParameter("Phone") + "<br>" + req.getParameter("email") + "<br>";
		menuItemsPage += req.getParameter("Address") + "<br>" + req.getParameter("City") + ", " + req.getParameter("state") + req.getParameter("Zip") + "<br>";
		menuItemsPage += "===================================<br>";
		for(int i = 0; i < cart.size(); i++){
			menuItemsPage += cart.get(i).getType() + " " + cart.get(i).getName() + " $" + String.format("%.2f", Double.parseDouble(cart.get(i).getPrice())) + "<br>";
			subtotal += Double.parseDouble(cart.get(i).getPrice());
		}
		menuItemsPage += "===================================<br>";
		menuItemsPage += " Sub Total $" + String.format("%.2f", (subtotal)) + "<br>";
		menuItemsPage += " Tax $" + String.format("%.2f", (subtotal*0.06)) + "<br>";
		menuItemsPage += " Total $" + (String.format("%.2f", subtotal+subtotal*0.06)) + "<br>";
		menuItemsPage += "===================================";
		menuItemsPage += "<br><button type='button' onclick='window.print();'>Print</button>";
		menuItemsPage += "\n\t\t\t<input type='submit' name='OnlineOrder' value='Home'/>";
		menuItemsPage += "\n</form></body></html>";
		saveData(req);
		itemSelected = 0;
		cart.clear();
		return menuItemsPage;
	}
	void saveData(HttpServletRequest req){
		try {
			
			FileWriter l = new FileWriter("order.txt",true);
			BufferedWriter listout = new BufferedWriter(l);
			listout.write(new Date().toString());
			listout.write("\n" + req.getParameter("firstName"));
			listout.write("\n" + req.getParameter("lastName"));
			listout.write("\n" + req.getParameter("Phone"));
			listout.write("\n" + req.getParameter("email"));
			listout.write("\n" + req.getParameter("Address"));
			listout.write("\n" + req.getParameter("City"));
			listout.write("\n" + req.getParameter("state"));
			listout.write("\n" + req.getParameter("Zip"));
			for(int i = 0; i < cart.size(); i++){
				listout.write("\n" + cart.get(i).getType() + "\n" + cart.get(i).getName() + "\n$" + String.format("%.2f", Double.parseDouble(cart.get(i).getPrice())));
			}
			listout.newLine();
			listout.close(); 
			l.close();
		}  
		catch (IOException e){ e.printStackTrace(); }
	}
}

class menu {
	ArrayList <menuItem> menuItems = new ArrayList<menuItem>();
	menu(){
		readFile(menuItems);
	}
	void readFile(ArrayList <menuItem> p) {	
		String s = new String();//String for File operation
		try {
			BufferedReader file = new BufferedReader(new FileReader("menu"));
			while((s = file.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(s, ",");
				if (st.countTokens() == 4)
					p.add(new menuItem(st.nextToken(),st.nextToken(),st.nextToken(),st.nextToken()));//add to ArrayList.
			}
			file.close();//Close file.
		}catch (IOException e) {e.printStackTrace();};
	}
}

class menuItem{
	private String type;	
	private String name;
	private String discription;
	private String price;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscription() {
		return discription;
	}
	public void setDiscription(String discription) {
		this.discription = discription;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	menuItem(String t, String n, String d,String p){
		type = t;
		name = n;
		discription = d;
		price = p;		
	}
	public String toString() { return type + "\t" + name + "\t" + discription + "\t" + price + "\n";  }
	
	
}

