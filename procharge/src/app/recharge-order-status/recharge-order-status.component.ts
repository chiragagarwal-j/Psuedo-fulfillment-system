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
  constructor(private rechargeSimService: RechargeSimService) { }
  ngOnInit(): void {
    this.rechargeSimService.getRechargeOrderDetails().subscribe((response: RechargeOrderStatus) => {
      this.rechargedSimInfo = response;
      console.log(this.rechargedSimInfo);
      
    });
  }

}
