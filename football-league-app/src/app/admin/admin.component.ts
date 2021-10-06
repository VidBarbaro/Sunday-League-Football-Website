import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Player } from '../model/Player';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  players: Player[] = [];

  constructor(private http: HttpClient) { }

  displayedColumns: string[] = ['firstName', 'lastName', 'country', 'club'];
  dataSource!: MatTableDataSource<Player>;

  ngOnInit(): void {
    this.getAllPlayers();
  }

  @ViewChild(MatSort) sort!: MatSort;

  ngAfterViewInit() {
    this.dataSource.sort = this.sort;
  }

  public getAllPlayers() {
    let url = "http://localhost:8080/players";
    this.http.get<Player[]>(url).subscribe(
      res => {
        this.players = res;
        this.dataSource = new MatTableDataSource(this.players);
      },
      err => {
        alert("An error has occured;")
      }
    )
  }

}
