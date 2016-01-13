package pl.wspa.student.wspacitibikenyc;

/**
 * Created by Grzegorz on 2016-01-13.
 */
public class StationMarker {
    private int id;
    private double lat;
    private double lon;
    private String station_name;
    private String description;


    public StationMarker(int id, double lat, double lon, String station_name, String description) {

        this.id=id;
        this.lat=lat;
        this.lon=lon;
        this.station_name=station_name;
        this.description=description;
    }
    public StationMarker(int id, double lat, double lon)
    {
        this.id=id;
        this.lat=lat;
        this.lon=lon;
    }
    public StationMarker(int id, double lat, double lon, String station_name)
    {
        this.id=id;
        this.lat=lat;
        this.lon=lon;
        this.station_name=station_name;
    }

    public int getId() {return id;}
    public double getLat() {return lat;}
    public double getLon() {return  lon;}
    public String getStation_name() {return station_name;}
    public String getDescrition() {return description;}

    public void setId(int id) {this.id=id;}
    public void setLat(double lat){this.lat=lat;}
    public void setLon(double lon){this.lon=lon;}
    public void setStation_name(String station_name){this.station_name=station_name;}
    public void setDescription(String descrition){this.description=descrition;}
}
