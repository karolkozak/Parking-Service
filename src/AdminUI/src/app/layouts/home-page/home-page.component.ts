import {Component, OnInit} from "@angular/core";
import {ParkingService} from "../../shared/services/parking.service";
import {CarPlate} from "../../shared/models/car-plate";
import {MatTableDataSource} from "@angular/material";

@Component({
  selector: 'ps-home-page',
  templateUrl: './home-page.component.html',
  styleUrls: ['./home-page.component.scss']
})
export class HomePageComponent implements OnInit {

  private carPlates: CarPlate[];
  dataSource: MatTableDataSource<CarPlate>;
  displayedColumns: string[];

  constructor(private parkingService: ParkingService) {
  }

  ngOnInit() {
    this.displayedColumns = ['id', 'entryTimestamp', 'plate', 'exitTimestamp'];
    this.parkingService.getAllData().subscribe(data => {
      this.carPlates = data;
      this.dataSource = new MatTableDataSource(this.carPlates);
    }, () => {
      this.carPlates = [
        {'id': 1, 'entryTimestamp': '20/05/2018', 'plate': 'WE 23455', 'exitTimestamp': '20/05/2018'},
        {'id': 2, 'entryTimestamp': '21/05/2018', 'plate': 'KR 23455', 'exitTimestamp': '22/05/2018'},
      ];
      this.dataSource = new MatTableDataSource(this.carPlates);
    });
  }

  getDate(date: any): string {
    return date
      ? `${date.dayOfMonth} ${date.month} ${date.year}: ${date.hour}:` +
        `${date.minute.toString().length === 1 ? date.minute.toString().padStart(2, "0") : date.minute}:${date.second}`
      : '';
  }
}
