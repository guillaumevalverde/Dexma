# Dexma App

The Vending machine has two behaviors, 
* user behavior
* Supplier behavior

the behaviors are represented by interfaces.

There is one implementation of Vending Machine.

the change Algo is a recursive one which go through all possibilitie of the tree, until he get one solution.

the change to give to the user when he cancel or ask for it is represented by a map Map<Coin, quantity>.

the algo considered that List<CoinInMachine> is sorted ASC. 
