import argparse
import logging

import RPi.GPIO as GPIO       
GPIO.setmode(GPIO.BCM)


from rpi_rf import RFDevice

class RfRpiCommand():

    def data_tx(code : int):
        rfdevice = RFDevice(17)
        rfdevice.enable_tx()
        rfdevice.tx_repeat = 10
        print('sent code:' + str(code))

        rfdevice.tx_code(code, 1, 500, 24)
        rfdevice.cleanup()
        
       
