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


//example of asynchronous fetch:
- (void)autoCompleteTextField:(MLPAutoCompleteTextField *)textField
 possibleCompletionsForString:(NSString *)string
            completionHandler:(void (^)(NSArray *))handler
{
    dispatch_queue_t queue = dispatch_get_global_queue(DISPATCH_QUEUE_PRIORITY_HIGH, 0);
    dispatch_async(queue, ^{
        if(self.simulateLatency){
            CGFloat seconds = arc4random_uniform(4)+arc4random_uniform(4); //normal distribution
            NSLog(@"sleeping fetch of completions for %f", seconds);
            sleep(seconds);
        }
        
        NSArray *completions;
        if(self.testWithAutoCompleteObjectsInsteadOfStrings){
            completions = [self allDrugObjects];
        } else {
            completions = [self allDrugs];
        }
        
        handler(completions);
    });
}

/*
 - (NSArray *)autoCompleteTextField:(MLPAutoCompleteTextField *)textField
 possibleCompletionsForString:(NSString *)string
 {
 
 if(self.simulateLatency){
 CGFloat seconds = arc4random_uniform(4)+arc4random_uniform(4); //normal distribution
 NSLog(@"sleeping fetch of completions for %f", seconds);
 sleep(seconds);
 }
 
 NSArray *completions;
 if(self.testWithAutoCompleteObjectsInsteadOfStrings){
 completions = [self allCountryObjects];
 } else {
 completions = [self allCountries];
 }
 
 return completions;
 }
 */

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
