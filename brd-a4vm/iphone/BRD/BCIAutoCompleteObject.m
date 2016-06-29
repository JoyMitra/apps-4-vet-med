//
//  BCIAutoCompleteObject.m
//  BRD
//
//  Created by Kevin Manase on 6/29/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

#import "BCIAutoCompleteObject.h"

@interface BCIAutoCompleteObject ()
@property (strong) NSString *drugName;
@end

@implementation BCIAutoCompleteObject


- (id)initWithDrugName:(NSString *)name
{
    self = [super init];
    if (self) {
        [self setDrugName:name];
    }
    return self;
}

#pragma mark - MLPAutoCompletionObject Protocl

- (NSString *)autocompleteString
{
    return self.drugName;
}

@end
