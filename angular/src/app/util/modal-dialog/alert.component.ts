export class AlertComponent {
    message: any;
    constructor(
      public router: Router, 
      private route: ActivatedRoute, 
      private alertService: AlertService,
   ) { }
   ngOnInit() {
    //this function waits for a message from alert service, it gets 
    //triggered when we call this from any other component
    this.alertService.getMessage().subscribe(message => {
        this.message = message;
    });
}
