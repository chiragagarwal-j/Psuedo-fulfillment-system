import { Component, OnInit } from '@angular/core';
import { RechargeSimService } from '../services/recharge-sim.service';
import { RechargeOrderStatus } from '../models/RechargeOrderStatus';

@Component({
  selector: 'app-recharge-order-status',
  templateUrl: './recharge-order-status.component.html',
  styleUrls: ['./recharge-order-status.component.css']
})
export class RechargeOrderStatusComponent implements OnInit {

  rechargedSimInfo?: RechargeOrderStatus;
  rechargeOrderID?: string;
  progressBarValue: number = 20;
  progressBarColor: string = 'accent';
  constructor(private rechargeSimService: RechargeSimService) { }
  ngOnInit(): void {
    this.rechargeOrderID = this.rechargeSimService.retriveOrderId();
    this.rechargeSimService.getRechargeOrderDetails(this.rechargeOrderID).subscribe((response: RechargeOrderStatus) => {
      this.rechargedSimInfo = response;
      this.progressBarValue = 100;
    });
  }

}