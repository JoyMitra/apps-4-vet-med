//
//  BCIDrugsDataSource.h
//  BRD
//
//  Created by Kevin Manase on 6/29/16.
//  Copyright © 2016 Beef Cattle Institute. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "MLPAutoCompleteTextFieldDataSource.h"

@interface BCIDrugsDataSource : NSObject <MLPAutoCompleteTextFieldDataSource>

//Set this to true to return an array of autocomplete objects to the autocomplete textfield instead of strings.
//The objects returned respond to the MLPAutoCompletionObject protocol.
@property (assign) BOOL testWithAutoCompleteObjectsInsteadOfStrings;


//Set this to true to prevent auto complete terms from returning instantly.
@property (assign) BOOL simulateLatency;
@end
