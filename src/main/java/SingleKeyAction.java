
import javax.swing.Action;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author halpin
 */
public class SingleKeyAction {
    private final String label;   
    private final Action action;
    private final String actionName; 

    
    public SingleKeyAction(String l, Action a) {
        label = l;
        action = a;
        actionName = l + "DefaultAction";
    }
    
    public String getActionName() {
        return actionName;
    }
    
    public String getLabel() {
        return label;
    }
    
    public Action getAction() {
        return action;
    }
}
