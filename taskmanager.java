import java.io.*;
import java.util.*;

public class taskmanager {
	public static void main(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("todo.txt"));
	        
			List<String> tasks = new ArrayList<String>();

				while(scanner.hasNextLine())
					tasks.add(scanner.nextLine());

			StringBuilder taskBuilder = new StringBuilder();

			for(int i = 1; i< args.length; i++) 
				taskBuilder.append(args[i] + " ");

			String taskInput = taskBuilder.toString();


			if(args[0].equals("-add") || args[0].equals("-a"))
				tasks.add(taskInput.toString());

			if(args[0].equals("-remove") || args[0].equals("-r"))
				tasks.removeIf(task -> task.toLowerCase().equals(taskInput.toLowerCase()));


			Writer output = new BufferedWriter(new FileWriter("todo.txt"));

				for (String line : tasks)
					output.write(line + System.lineSeparator());
			tasks.forEach(task -> System.out.println(task));

			output.close();
		}catch(Exception e){
			System.out.println("Options: -list ; -add newTask; -remove newTask");
		}
	}
}
