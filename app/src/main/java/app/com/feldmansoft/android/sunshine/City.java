
package app.com.feldmansoft.android.sunshine;

import com.google.gson.annotations.Expose;


public class City {

    @Expose
    private Integer id;
    @Expose
    private String name;
    @Expose
    private Coord coord;
    @Expose
    private String country;
    @Expose
    private Integer population;

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public City withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public City withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * 
     * @return
     *     The coord
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * 
     * @param coord
     *     The coord
     */
    public void setCoord(Coord coord) {
        this.coord = coord;
    }

    public City withCoord(Coord coord) {
        this.coord = coord;
        return this;
    }

    /**
     * 
     * @return
     *     The country
     */
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     *     The country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    public City withCountry(String country) {
        this.country = country;
        return this;
    }

    /**
     * 
     * @return
     *     The population
     */
    public Integer getPopulation() {
        return population;
    }

    /**
     * 
     * @param population
     *     The population
     */
    public void setPopulation(Integer population) {
        this.population = population;
    }

    public City withPopulation(Integer population) {
        this.population = population;
        return this;
    }

}
