package imageProcessor;

public class ProcessMenu implements Menu {
	
	private String menuName;
	private String actionCommandName;
	
	public ProcessMenu(String name, String commandName) {
		
		this.menuName = name;
		this.actionCommandName = commandName;
	}
	
	@Override
	public String getName() {
		
		return menuName;
	}
	@Override
	public String getActionCommandName() {
		
		return actionCommandName;
	}
}
