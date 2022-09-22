import java.io.*;
import java.util.*;


class TaskManager{
	List<Option> options = new ArrayList<Option>();
	
	void execute(String[] args) {
		try {
			Scanner scanner = new Scanner(new File("todo.txt"));
	        
	        List<String> tasks = new ArrayList<String>();
			
			while(scanner.hasNextLine())
				tasks.add(scanner.nextLine());
	        
	        StringBuilder taskBuilder = new StringBuilder();
	        
	        for(int i = 1; i< args.length; i++) 
	        	taskBuilder.append(args[i] + " ");
	        
	        String taskInput = taskBuilder.toString();
	        
	        
	        for (Option option : options)
	        	if(option.possibleCalls.contains(args[0]))
	        		tasks = option.execute(tasks, taskInput);
			
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

class App1 extends TaskManager{
	public App1() {
		Option addOption = new AddTaskOption();
		addOption.possibleCalls.addAll(Arrays.asList("-a", "-add"));
		options.add(addOption);
	}
}


class App2 extends App1{
	public App2() {
		super();
		Option removeOption = new RemoveTaskOption();
		removeOption.possibleCalls.addAll(Arrays.asList("-r", "-remove"));
		options.add(removeOption);
		options.add(new RemoveTaskOption());
	}
}

public class ocptaskmanager {
	public static void main(String[] args) {
		TaskManager taskManager = new App2();
		taskManager.execute(args);
	}
}


abstract class Option{
	List<String> possibleCalls = new ArrayList<String>();
	abstract List<String> execute(List<String> tasks, String taskInput);
}


class AddTaskOption extends Option{

	@Override
	List<String> execute(List<String> tasks, String taskInput) {
		tasks.add(taskInput);
		return tasks;
	}
}

class RemoveTaskOption extends Option{

	@Override
	List<String> execute(List<String> tasks, String taskInput) {
		tasks.removeIf(task -> task.toLowerCase().equals(taskInput.toLowerCase()));
		return tasks;
	}
}