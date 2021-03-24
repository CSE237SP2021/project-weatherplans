package weatherapp;

public class menu {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if(args.length == 1) {
			if(args[0].equals("--help")) {
				System.out.println("get help lmao");
			}
		}
		else {
			System.out.println("we doing other stuff");
		}
	}

}
