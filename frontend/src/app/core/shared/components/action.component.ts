import { Component, EventEmitter, Input, Output } from "@angular/core";

@Component({
    selector: 'app-actions',
    template: `    
    <button class="btn btn-sm btn-link"  *ngIf="isEditEnabled" (click)="onEdit()"> Edit </button>
    <button class="btn btn-sm btn-link" *ngIf="isDeleteEnabled" (click)="onDelete()">  Delete </button>
    `
})
export class ActionComponent {
    @Input() cell: any;
    @Input() isEditEnabled: boolean = false;
    @Input() isDeleteEnabled: boolean = false;
    @Output() onEditClicked = new EventEmitter<boolean>();
    @Output() onDeleteClicked = new EventEmitter<boolean>();
    @Output() onViewClicked = new EventEmitter<boolean>();

    onView(): void {
        this.onViewClicked.emit(this.cell);
    }

    onEdit(): void {
        this.onEditClicked.emit(this.cell);
    }

    onDelete(): void {
        this.onDeleteClicked.emit(this.cell);
    }
}