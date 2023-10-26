import { Component, OnInit } from '@angular/core';
import { RechargeSimService } from '../services/recharge-sim.service';
import { RechargeSimStatus } from '../models/RechargeSimStatus';

@Component({
  selector: 'app-recharge-order-status',
  templateUrl: './recharge-order-status.component.html',
  styleUrls: ['./recharge-order-status.component.css']
})
export class RechargeOrderStatusComponent implements OnInit {

  rechargedSimInfo?: RechargeSimStatus;
  constructor(private rechargeSimService: RechargeSimService) { }
  ngOnInit(): void {
  }


}
