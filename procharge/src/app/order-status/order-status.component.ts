import { Component, OnInit } from '@angular/core';
import { NewSimService } from '../services/new-sim.service';
import { NewSimOrderStatus } from '../models/NewSimOrderStatus';

@Component({
  selector: 'app-order-status',
  templateUrl: './order-status.component.html',
  styleUrls: ['./order-status.component.css']
})
export class OrderStatusComponent implements OnInit {

  allOrderDetails?: NewSimOrderStatus;
  cachedOrderID?: string;
  progressBarValue: number = 20;
  progressBarColor: string = 'accent';
  constructor(private newSimService: NewSimService) { }

  ngOnInit() {
    this.cachedOrderID = this.newSimService.retriveOrderId();
    this.progressBarValue = 50;
    this.newSimService.fetchOrderDetails().subscribe((data) => {
      this.allOrderDetails = data;
      this.progressBarValue = 100;
    });

  }

}