package telegramBot;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONSaver {

	public JSONSaver() {
		print("initialized", 1);
	}
	
	@SuppressWarnings("unchecked")
	public void save(GroupHandler handler){
		print("Saving Triggers...", 2);
		
		ArrayList<TriggerHandler> thandlers = handler.getTriggerHandlers();
		
		JSONArray arr = new JSONArray();
		for(TriggerHandler handl : thandlers) {
			JSONObject h = new JSONObject();
			h.put("groupId", handl.getGroupId());
			h.put("groupName", handl.getGroupName());
			
			JSONArray triggers = new JSONArray();
			for(Trigger t : handl.getTriggers()){
				JSONObject d = new JSONObject();
				d.put("triggerWord", t.getTrigger());
				d.put("message", t.getMessage());
				d.put("creator", t.getCreator());
				d.put("isPhoto", t.isPhoto());
				JSONObject e = new JSONObject();
				e.put("trigger", d);
				triggers.add(e);
			}
			h.put("triggers",triggers);
			JSONObject f = new JSONObject();
			f.put("triggerHandler", h);
			arr.add(f);
		}
		
		/*try (FileWriter file = new FileWriter("triggers.json")) {
			
            file.write(arr.toJSONString());
            file.flush();
 
        } catch (IOException e) {
            e.printStackTrace();
        }*/
		
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("triggers.json"), Charset.forName("UTF-8"))){
			writer.write(arr.toJSONString());
			writer.flush();
			print("Saved " + handler.getTotalNumberOfTriggers() + " Triggers!", 1);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			print("ERROR: ", 3);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			print("ERROR: ", 3);
			e.printStackTrace();
		}
	}
	
	
	public ArrayList<TriggerHandler> load(){
		JSONParser jsonParser = new JSONParser();
		ArrayList<TriggerHandler> handlers = new ArrayList<TriggerHandler>();
        
        try (InputStreamReader reader = new InputStreamReader( new FileInputStream("triggers.json"), Charset.forName("UTF-8")))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray arr = (JSONArray) obj;
            //arr.forEach(trig -> parseTrig((JSONObject) trig, handlers));
            
            for(int i = 0; i < arr.size(); i++) {
            	parseTrig((JSONObject)((JSONObject) (arr.get(i))).get("triggerHandler"), handlers);
            }
             
 
        } catch (FileNotFoundException e) {
        	print("ERROR: ", 3);
            e.printStackTrace();
        } catch (IOException e) {
        	print("ERROR: ", 3);
            e.printStackTrace();
        } catch (ParseException e) {
        	print("ERROR: ", 3);
            e.printStackTrace();
        }
        
        return handlers;
    }
	
	@SuppressWarnings("unchecked")
	public void saveAdmins(ArrayList<UserAdmin> userAdmins) {
		print("Saving Admins...", 2);

		
		JSONArray arr = new JSONArray();
		for(UserAdmin user : userAdmins) {
			JSONObject h = new JSONObject();
			h.put("name", user.getName());
			h.put("id", user.getId());
			h.put("group", user.currentGroupId);
			JSONObject e = new JSONObject();
			e.put("admin", h);
			arr.add(e);
		}
		
		
		try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream("admins.json"), Charset.forName("UTF-8"))){
			writer.write(arr.toJSONString());
			writer.flush();
			print("Saved " + userAdmins.size() + " Admins!", 1);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			print("ERROR: ", 3);
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			print("ERROR: ", 3);
			e.printStackTrace();
		}
	}
	
	public ArrayList<UserAdmin> loadAdmins(){
		print("Loading Admins...", 2);
		JSONParser jsonParser = new JSONParser();
		ArrayList<UserAdmin> userAdmins = new ArrayList<UserAdmin>();
        
        try (InputStreamReader reader = new InputStreamReader( new FileInputStream("admins.json"), Charset.forName("UTF-8")))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray arr = (JSONArray) obj;
            //arr.forEach(trig -> parseTrig((JSONObject) trig, handlers));
            
            for(int i = 0; i < arr.size(); i++) {
            	JSONObject jsonuser = (JSONObject)((JSONObject) (arr.get(i))).get("admin");
            	
            	userAdmins.add(new UserAdmin((long)(jsonuser.get("id")), (String)jsonuser.get("name"), (long)jsonuser.get("group")));
            	
            }
             
 
        } catch (FileNotFoundException e) {
        	print("ERROR: ", 3);
            e.printStackTrace();
        } catch (IOException e) {
        	print("ERROR: ", 3);
            e.printStackTrace();
        } catch (ParseException e) {
        	print("ERROR: ", 3);
            e.printStackTrace();
        }
        print("Loaded " + userAdmins.size() + " admins!", 1);
        
        return userAdmins;
	}
	
	
	
	private void parseTrig(JSONObject trig, ArrayList<TriggerHandler> handlers){
		JSONArray jsontriggers = (JSONArray) trig.get("triggers");
		ArrayList<Trigger> triggerList = new ArrayList<Trigger>();
		print("Loading Triggers for " + trig.get("groupName") + "...", 2);
		
		for(int i = 0; i < jsontriggers.size(); i++) {
			JSONObject trigger = (JSONObject) jsontriggers.get(i);
			JSONObject attributes = (JSONObject) trigger.get("trigger");
			
			Trigger temp = new Trigger((String)attributes.get("triggerWord"),(String)attributes.get("message"),(String)attributes.get("creator"),(boolean)attributes.get("isPhoto"));
			//print("LOADED: " + temp, 2);
			triggerList.add(temp);
		}
		
		handlers.add(new TriggerHandler(triggerList, (long)(trig.get("groupId")), (String)(trig.get("groupName"))));
		print("Loaded " + triggerList.size() + " triggers for " + trig.get("groupName") + "!", 1);
	}
	
	 private void print(Object o, int errorstate) {
			BotUtilities.print("JSONSaver",o.toString(), errorstate);
	}

}
