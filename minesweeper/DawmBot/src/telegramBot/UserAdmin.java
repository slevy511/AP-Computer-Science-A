package telegramBot;

public class UserAdmin {
	private String name;
	private long id;
	public int currentMenu;
	public long currentGroupId;
	
	
	public UserAdmin(long id, String name) {
		this.name = name;
		this.id = id;
		currentMenu = 0;
		currentGroupId = 0;
	}
	
	public UserAdmin(long id, String name, long group) {
		this.name = name;
		this.id = id;
		currentMenu = 0;
		currentGroupId = group;
	}

	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String n) {
		name = n;
	}
	
	public String toString() {
		return "name: " + name + " id: " + id;
	}
	
	
}
