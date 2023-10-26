import { Component, OnInit } from '@angular/core';
import { NewSimService } from '../services/new-sim.service';

@Component({
  selector: 'app-order-status',
  templateUrl: './order-status.component.html',
  styleUrls: ['./order-status.component.css']
})
export class OrderStatusComponent implements OnInit {

  allOrderDetails: any;
  constructor(private newSimService: NewSimService) { }

  ngOnInit() {
    this.getOrderDetails();
  }

  getOrderDetails() {
    this.newSimService.fetchOrderDetails().subscribe((data) => {
      this.allOrderDetails = data;
    });
  }

}