import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {AvonnivSharedModule} from '../shared';

import {grantsRoute, GrantsComponent} from './';
import {GrantsResolvePagingParams} from './grants.route';

@NgModule({
    imports: [
        AvonnivSharedModule,
        RouterModule.forRoot(grantsRoute, {useHash: true})
    ],
    declarations: [
        GrantsComponent
    ],
    entryComponents: [],
    providers: [
        GrantsResolvePagingParams
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AvonnivGrantsModule {
}