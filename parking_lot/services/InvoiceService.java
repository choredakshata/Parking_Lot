package parking_lot.services;

import parking_lot.exceptions.InvalidGateException;
import parking_lot.exceptions.InvalidTicketException;
import parking_lot.models.Invoice;

public interface InvoiceService {

    public Invoice generateInvoice(int ticketId, int gateId) throws InvalidTicketException, InvalidGateException;
}
