package service;

import dao.GasStationDAO;
import dto.GasStationDTO;
import dto.MapperDTO;
import dto.PersonDTO;
import entity.GasStationBuilder;
import entity.PersonBuilder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<GasStationDTO> getGasStationListAsDTO() throws SQLException {
       return gasStationDAO.getAllStations()
                .stream()
                .map(MapperDTO::toGasStationDTO)
                .collect(Collectors.toList());
    }

    public GasStationDTO convertToGasStationDTO(GasStationBuilder stationBuilder) throws SQLException, IOException {
        return MapperDTO.toGasStationDTO(stationBuilder);
    }

    public GasStationBuilder convertToGasStation(GasStationDTO stationBuilder) throws SQLException, IOException {
        return MapperDTO.toGasStationBuilder(stationBuilder);
    }

    public void createGasStationDTO(GasStationDTO stationDTO) throws SQLException, IOException {
        gasStationDAO.addStation(convertToGasStation(stationDTO));
    }

}
