package pl.wspa.student.wspacitibikenyc;

import android.location.Location;

import java.util.Comparator;
import java.util.Random;

/**
 * Created by Tomasz on 17.09.2015.
 * Stolen by Piotrek on 2015-11-04.
 */
public class Station implements Comparable<Station> {
    private String id;
    private String stationName;
    private String availableDocks;
    private String totalDocks;
    private double latitude;
    private double longitude;
    private String statusValue;
    private int statusKey;
    private String availableBikes;
    private String stAddress1;
    private String stAddress2;
    private String city;
    private String postalCode;
    private String location;
    private String altitude;
    private String testStation;
    private String lastCommunicationTime;
    private String landMark;
    private double distance;

    public Station(String id, String stationName, String availableDocks, String totalDocks, String latitude, String longitude, String statusValue, String statusKey, String availableBikes, String stAddress1, String stAddress2, String city, String postalCode, String location, String altitude, String testStation, String lastCommunicationTime, String landMark) {
        this(id,
                stationName,
                availableDocks,
                totalDocks,
                Double.parseDouble(latitude),
                Double.parseDouble(longitude),
                statusValue,
                Integer.parseInt(statusKey),
                availableBikes,
                stAddress1,
                stAddress2,
                city,
                postalCode,
                location,
                altitude,
                testStation,
                lastCommunicationTime,
                landMark);
    }

    public Station(String id, String stationName, String availableDocks, String totalDocks, double latitude, double longitude, String statusValue, int statusKey, String availableBikes, String stAddress1, String stAddress2, String city, String postalCode, String location, String altitude, String testStation, String lastCommunicationTime, String landMark) {
        this.id = id;
        this.stationName = stationName;
        this.availableDocks = availableDocks;
        this.totalDocks = totalDocks;
        this.latitude = latitude;
        this.longitude = longitude;
        this.statusValue = statusValue;
        if (statusKey == 3)
            if (lastCommunicationTime.equals("1969-12-31 07:00:00 PM"))
                statusKey = 2;
        this.statusKey = statusKey;
        this.availableBikes = availableBikes;
        this.stAddress1 = stAddress1;
        this.stAddress2 = stAddress2;
        this.city = city;
        this.postalCode = postalCode;
        this.location = location;
        this.altitude = altitude;
        this.testStation = testStation;
        this.lastCommunicationTime = lastCommunicationTime;
        this.landMark = landMark;
        //TODO zmienic na automatyczne obliczanie
        double d = new Random().nextDouble() * 10000.0;
        this.distance = d - (d % 1.0);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getAvailableDocks() {
        return availableDocks;
    }

    public void setAvailableDocks(String availableDocks) {
        this.availableDocks = availableDocks;
    }

    public String getTotalDocks() {
        return totalDocks;
    }

    public void setTotalDocks(String totalDocks) {
        this.totalDocks = totalDocks;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public int getStatusKey() {
        return statusKey;
    }

    public void setStatusKey(int statusKey) {
        this.statusKey = statusKey;
    }

    public String getAvailableBikes() {
        return availableBikes;
    }

    public void setAvailableBikes(String availableBikes) {
        this.availableBikes = availableBikes;
    }

    public String getStAddress1() {
        return stAddress1;
    }

    public void setStAddress1(String stAddress1) {
        this.stAddress1 = stAddress1;
    }

    public String getStAddress2() {
        return stAddress2;
    }

    public void setStAddress2(String stAddress2) {
        this.stAddress2 = stAddress2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getTestStation() {
        return testStation;
    }

    public void setTestStation(String testStation) {
        this.testStation = testStation;
    }

    public String getLastCommunicationTime() {
        return lastCommunicationTime;
    }

    public void setLastCommunicationTime(String lastCommunicationTime) {
        this.lastCommunicationTime = lastCommunicationTime;
    }

    public String getLandMark() {
        return landMark;
    }

    public void setLandMark(String landMark) {
        this.landMark = landMark;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double d) {
        this.distance = distance;
    }

    /**
     * Metoda aktualizuje odległość od pozycji do stacji.
     *
     * @param latitude  szerokość geograficzna.
     * @param longitude długość geograficzna.
     */
    public void updateDistance(double latitude, double longitude) {
        float[] results = new float[1];
        Location.distanceBetween(this.getLatitude(), this.getLongitude(),
                latitude, longitude, results);
    }

    /**
     * Metoda aktualizuje odległość od pozycji (GPS) do stacji.
     */
    public void updateDistance() {
        updateDistance(MainActivity.gpsLatitude, MainActivity.gpsLongitude);
    }

    @Override
    public int compareTo(Station s) {
        if (s == null)
            return -1;
        int result = Integer.valueOf(id) - Integer.valueOf(s.getId());
        if (result == 0)
            return statusKey - s.statusKey;
        else
            return result;
    }

    public static class DistanceComparator implements Comparator<Station> {
        public final static boolean ASC = true, DESC = false;
        private boolean order;

        public DistanceComparator(boolean order) {
            this.order = order;
        }

        private int comp(Station x, Station y) {
            if (x == null || y == null)
                return -1;
            double result = x.getDistance() - y.getDistance();
            if (result == 0)
                return new NameComparator(order).compare(x, y);
            else
                return result < 0 ? -1 : 1;
        }

        @Override
        public int compare(Station x, Station y) {
            if (order) {
                return comp(x, y);
            } else {
                return comp(y, x);
            }
        }
    }

    public static class NameComparator implements Comparator<Station> {
        public final static boolean ASC = true, DESC = false;
        private boolean order;

        public NameComparator(boolean order) {
            this.order = order;
        }

        private int comp(Station x, Station y) {
            if (x == null || y == null)
                return -1;
            return x.stationName.compareToIgnoreCase(y.stationName);
        }

        @Override
        public int compare(Station x, Station y) {
            if (order) {
                return comp(x, y);
            } else {
                return comp(y, x);
            }
        }
    }
}

