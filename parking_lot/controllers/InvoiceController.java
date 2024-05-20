package parking_lot.controllers;

import parking_lot.dtos.*;
import parking_lot.exceptions.InvalidRequestException;
import parking_lot.models.Invoice;
import parking_lot.services.InvoiceService;

public class InvoiceController {

    private InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    public GenerateInvoiceResponseDto generateInvoice(GenerateInvoiceRequestDto requestDto){
        GenerateInvoiceResponseDto responseDto = new GenerateInvoiceResponseDto();
        try{
            if(requestDto.getTicketId() < 0){
                throw  new InvalidRequestException("Ticket id cannot be negative");
            }
            if(requestDto.getGateId() < 0){
                throw  new InvalidRequestException("Gate id cannot be negative");
            }
        } catch (Exception e){
            Response response = new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }

        try{
            Invoice invoice = invoiceService.generateInvoice(requestDto.getTicketId(), requestDto.getGateId());
            Response response = new Response();
            response.setStatus(ResponseStatus.SUCCESS);
            responseDto.setInvoice(invoice);
            responseDto.setResponse(response);
            return responseDto;
        } catch (Exception e){
            Response response = new Response();
            response.setStatus(ResponseStatus.FAILED);
            response.setError(e.getMessage());
            responseDto.setResponse(response);
            return responseDto;
        }


    }
}
