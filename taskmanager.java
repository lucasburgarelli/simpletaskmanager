import java.io.*;
import java.util.*;

public class taskmanager {
	public static void main(String[] args) {
		String help = System.lineSeparator()+"0 -> list"+System.lineSeparator()+"1 + task -> add"+System.lineSeparator()+"2 + task -> remove";
		
		String task = "";
		for(int i = 1; i< args.length; i++)
			task += args[i] + " ";
			
		try {
	        Scanner scanner = new Scanner(new File("todo.txt"));
	        
	        if(args[0].equals("0")) {
	        	while(scanner.hasNextLine())
					System.out.println(scanner.nextLine());
	        	
				return;
	        }
	        
	        if(args[0].equals("1")) {
	        	Writer output = new BufferedWriter(new FileWriter("todo.txt", true));
				output.append(task + System.lineSeparator());
				output.close();
				return;
	        }
	        
	        if(args[0].equals("2")) {
				ArrayList<String> updatedList = new ArrayList<String>();
				
				while(scanner.hasNextLine()) {
					String line = scanner.nextLine();
					
					if(!line.toLowerCase().equals(task.toLowerCase()))
						updatedList.add(line);
				}
				
				Writer output = new BufferedWriter(new FileWriter("todo.txt"));
				for (String line : updatedList) 
					output.write(line + System.lineSeparator());
				output.close();
				
				return;
	        }
	        
	        System.out.print("Unknown command" + help);
		}catch(Exception e){
			System.out.print("Error" + help);
		}
	}
}