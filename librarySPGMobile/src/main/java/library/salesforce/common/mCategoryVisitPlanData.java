package library.salesforce.common;

import java.io.Serializable;

/**
 * Created by Robert on 03/05/2017.
 */

public class mCategoryVisitPlanData implements Serializable {
    private String intCategoryVisitPlan;
    private String txtCatVisitPlan;
    private String bitActive;

    public String Property_intCategoryVisitPlan = "intCategoryVisitPlan";
    public String Property_txtCatVisitPlan = "txtCatVisitPlan";
    public String Property_bitActive = "bitActive";

    public String Property_All = Property_intCategoryVisitPlan + "," + Property_txtCatVisitPlan + "," + Property_bitActive;

    public synchronized String getIntCategoryVisitPlan(){
        return intCategoryVisitPlan;
    }
    public synchronized void setIntCategoryVisitPlan(String intCategoryVisitPlan){
        this.intCategoryVisitPlan = intCategoryVisitPlan;
    }

    public synchronized String getTxtCatVisitPlan(){
        return txtCatVisitPlan;
    }
    public synchronized void setTxtCatVisitPlan(String txtCatVisitPlan){
        this.txtCatVisitPlan = txtCatVisitPlan;
    }

    public synchronized String getBitActive(){
        return bitActive;
    }
    public synchronized void setBitActive(String bitActive){
        this.bitActive = bitActive;
    }

}
