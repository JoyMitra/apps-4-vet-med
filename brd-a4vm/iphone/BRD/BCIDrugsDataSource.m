//
//  BCIDrugsDataSource.m
//  BRD
//
//  Created by Kevin Manase on 6/29/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

#import "BCIDrugsDataSource.h"
#import "BCIAutoCompleteObject.h"


@interface BCIDrugsDataSource ()

@property (strong, nonatomic) NSArray *drugObjects;

@end


@implementation BCIDrugsDataSource


#pragma mark - MLPAutoCompleteTextField DataSource


// Returns a list of text completion
- (NSArray *)allDrugObjects
{
    if(!self.drugObjects){
        NSArray *drugNames = [self allDrugs];
        NSMutableArray *mutableDrugs = [NSMutableArray new];
        for(NSString *drugName in drugNames){
            BCIAutoCompleteObject *drug = [[BCIAutoCompleteObject alloc] initWithDrugName:drugName];
            [mutableDrugs addObject:drug];
        }
        
        [self setDrugObjects:[NSArray arrayWithArray:mutableDrugs]];
    }
    
    return self.drugObjects;
}


// Drug data source
// Add more as needed
- (NSArray *)allDrugs
{
    NSArray *drugs =
    @[
      @"Terramycin",
      @"Liquamycin",
      @"Agrimycin",
      @"Oxytet",
      @"LA-200",
      @"Procure",
      @"Biocor",
      @"Tetradure 300",
      @"Erythro-200",
      @"Erythromycin-200",
      @"Gallimycin-200",
      @"Tylan",
      @"Tylosin Injection",
      @"Naxcel",
      @"Micotil 300",
      @"Nuflor",
      @"Zactran",
      @"Draxxin",
      @"Baytril",
      @"Excenel",
      @"Naxcel",
      @"Albon",
      @"Zubrevo",
      @"Oxytetracycline",
      @"Erythromycin",
      @"Tylosin",
      @"Ceftoifur",
      @"Tilmicosin",
      @"Florfenicol",
      @"Gamithromycin",
      @"Tulathromycin",
      @"Enrofloxacin",
      @"Ceftiofur Hydrocloride",
      @"Ceftiofur Sodium",
      @"Sulfadimethoxine",
      @"Tildipirosin",
      ];
    
    return drugs;
}

@end
