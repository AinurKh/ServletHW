package service;

import dao.GasStationDAO;
import entity.GasStationBuilder;

import java.sql.SQLException;
import java.util.List;

public class GasStationService {
    private final GasStationDAO gasStationDAO;

    public GasStationService(GasStationDAO gasStationDAO) {
        this.gasStationDAO = gasStationDAO;
    }

    public List<GasStationBuilder> getGasStations() throws SQLException {
        return gasStationDAO.getAllStations();
    }

    public GasStationBuilder getGasStation(int id) throws SQLException {
        return gasStationDAO.getStationById(id);
    }

    public void addGasStation(GasStationBuilder gasStation) throws SQLException {
        gasStationDAO.addStation(gasStation);
    }

    public void updateGasStation(GasStationBuilder gasStation, int id) throws SQLException {
        gasStationDAO.updateStation(gasStation, id);
    }

    public void deleteGasStation(int id) throws SQLException {
        gasStationDAO.deleteStation(id);
    }

}
