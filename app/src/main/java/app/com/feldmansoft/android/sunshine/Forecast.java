
package app.com.feldmansoft.android.sunshine;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;


public class Forecast {

    @Expose
    private String cod;
    @Expose
    private Double message;
    @Expose
    private City city;
    @Expose
    private Integer cnt;
    @Expose
    private java.util.List<app.com.feldmansoft.android.sunshine.List> list = new ArrayList<app.com.feldmansoft.android.sunshine.List>();

    /**
     * 
     * @return
     *     The cod
     */
    public String getCod() {
        return cod;
    }

    /**
     * 
     * @param cod
     *     The cod
     */
    public void setCod(String cod) {
        this.cod = cod;
    }

    public Forecast withCod(String cod) {
        this.cod = cod;
        return this;
    }

    /**
     * 
     * @return
     *     The message
     */
    public Double getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(Double message) {
        this.message = message;
    }

    public Forecast withMessage(Double message) {
        this.message = message;
        return this;
    }

    /**
     * 
     * @return
     *     The city
     */
    public City getCity() {
        return city;
    }

    /**
     * 
     * @param city
     *     The city
     */
    public void setCity(City city) {
        this.city = city;
    }

    public Forecast withCity(City city) {
        this.city = city;
        return this;
    }

    /**
     * 
     * @return
     *     The cnt
     */
    public Integer getCnt() {
        return cnt;
    }

    /**
     * 
     * @param cnt
     *     The cnt
     */
    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public Forecast withCnt(Integer cnt) {
        this.cnt = cnt;
        return this;
    }

    /**
     * 
     * @return
     *     The list
     */
    public java.util.List<app.com.feldmansoft.android.sunshine.List> getList() {
        return list;
    }

    /**
     * 
     * @param list
     *     The list
     */
    public void setList(java.util.List<app.com.feldmansoft.android.sunshine.List> list) {
        this.list = list;
    }

    public Forecast withList(java.util.List<app.com.feldmansoft.android.sunshine.List> list) {
        this.list = list;
        return this;
    }

}
