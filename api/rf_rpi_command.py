import argparse
import logging
from rpi_rf import RFDevice

import RPi.GPIO as GPIO
GPIO.setmode(GPIO.BCM)

class RfRpiCommand():

    def data_tx(code : int, rfpin: int):
        rfdevice = RFDevice(rfpin)
        rfdevice.enable_tx()
        rfdevice.tx_repeat = 10
        print('sent code:' + str(code))

        rfdevice.tx_code(code, 1, 500, 24)
        rfdevice.cleanup()
