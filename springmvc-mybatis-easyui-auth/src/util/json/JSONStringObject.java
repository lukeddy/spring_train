package util.json;

import org.json.JSONString;

public class JSONStringObject implements JSONString{

    private String jsonString = null;
    
    public JSONStringObject(String jsonString){
        this.jsonString = jsonString;
    }

    @Override
    public String toString(){
        return jsonString;
    }

    public String toJSONString(){
        return jsonString;
    }
}
