import { Component } from '@angular/core';
import { NewSimService } from '../services/new-sim.service';
import { NewSimOrderStatus } from '../models/NewSimOrderStatus';

@Component({
  selector: 'app-track-order',
  templateUrl: './track-order.component.html',
  styleUrls: ['./track-order.component.css']
})
export class TrackOrderComponent {

  showOrderDetails: boolean = false;
  orderID: string = '';
  enteredOTP: string = '';
  mobileNumber: string = '';
  otpSent: boolean = false;
  otpverified: boolean = false;
  allOrderDetails?: NewSimOrderStatus;
  invalidOTPMessage: boolean = false;

  constructor(private newSimService: NewSimService) { }

  sendOTP() {
    this.newSimService.sendOTPtoMobile(this.orderID).subscribe(response => {
      this.mobileNumber = response.mobileNumber;
      this.mobileNumber = this.mobileNumber.slice(-4);
      this.otpSent = true;
    });
  }

  verifyOTP() {
    this.newSimService.validateOTPandFetchInfo(this.orderID, this.enteredOTP, '8008882598').subscribe(
      (data) => {
        if (data === 'Invalid OTP. Please try again.') {
          this.invalidOTPMessage = true;
        } else {
          this.allOrderDetails = data;
          this.otpverified = true;
          this.showOrderDetails = true;
        }
      }
    );
  }

}
