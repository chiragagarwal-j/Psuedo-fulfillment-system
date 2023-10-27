import { Component, OnInit } from '@angular/core';
import { RechargeSimService } from '../services/recharge-sim.service';
import { RechargeInfo } from '../models/RechargeInfo';
import { Router } from '@angular/router';

@Component({
  selector: 'app-payment-page',
  templateUrl: './payment-page.component.html',
  styleUrls: ['./payment-page.component.css']
})
export class PaymentPageComponent implements OnInit {
  orderAmount: number = 100;
  selectedPaymentMethod: string = '';
  upiId: string = '';
  cardNumber: string = '';
  selectedWallet: string = '';
  selectedUpiMethod: string = '';
  selectedCardType: string = '';
  expiryDate: string = '';
  cvv: string = '';
  cardName: string = '';

  rechargingInfo: RechargeInfo = { mobileNumber: '', operator: '', operatorCircle: '', planID: 1, payVia: '', paymentInfo: '', amount: '', orderID: '' };
  orderID: string = '';
  constructor(private rechargeSimService: RechargeSimService, private router: Router) { }
  ngOnInit(): void {
    this.rechargeSimService.getOrderID().subscribe(id => { this.orderID = id.orderID });
    this.rechargingInfo = this.rechargeSimService.retrieveCachedPlans();
  }

  processPayment() {
    this.rechargingInfo.payVia = this.selectedPaymentMethod;
    if (this.selectedPaymentMethod === 'upi') {
      this.rechargingInfo.paymentInfo = this.upiId;
    } else if (this.selectedPaymentMethod === 'card') {
      this.rechargingInfo.paymentInfo = this.cardNumber;
    } else if (this.selectedPaymentMethod === 'wallet') {
      this.rechargingInfo.paymentInfo = this.selectedWallet;
    }
    this.rechargingInfo.orderID = this.orderID;
    this.rechargeSimService.setOrderId(this.orderID);
    this.rechargeSimService.processRecharge(this.rechargingInfo).subscribe(() => {
      this.router.navigate(['/recharge-order-status']);
    });

  }
}
