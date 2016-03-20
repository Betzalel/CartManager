package edu.gatech.seclass.scm.service.impl;

import edu.gatech.seclass.scm.service.QRCodeScanService;
import edu.gatech.seclass.services.QRCodeService;

/**
 * Created by shivendrasrivastava on 10/22/15.
 */
public class QRCodeScanServiceImpl implements QRCodeScanService {

    public String scanCustomerCard(){
        return QRCodeService.scanQRCode();
    }
}
