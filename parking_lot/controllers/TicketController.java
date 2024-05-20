package parking_lot.controllers;

import parking_lot.dtos.GenerateTicketRequestDto;
import parking_lot.dtos.GenerateTicketResponseDto;
import parking_lot.dtos.Response;
import parking_lot.dtos.ResponseStatus;
import parking_lot.exceptions.InvalidRequestException;
import parking_lot.models.Ticket;
import parking_lot.services.TicketService;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public GenerateTicketResponseDto generateTicket(GenerateTicketRequestDto requestDto){
        GenerateTicketResponseDto responseDto = new GenerateTicketResponseDto();
        try {
            if (requestDto.getGateId() < 0) {
                throw new InvalidRequestException("Invalid gate id");
            }
            if (requestDto.getVehicleType() == null || requestDto.getVehicleType().equals("")) {
                throw new InvalidRequestException("Vehicle type is mandatory");
            }
        } catch (InvalidRequestException e){
            Response response = new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

        Response response = new Response();
        try {
            Ticket ticket = ticketService.generateTicket(requestDto.getGateId(), requestDto.getVehicleNumber(), requestDto.getVehicleType());
            responseDto.setTicket(ticket);
            response.setStatus(ResponseStatus.SUCCESS);
        } catch (Exception e){
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
        }
        responseDto.setResponse(response);
        return responseDto;

    }
}
