/*Do not manually edit this file unless it is to clean up the end section*/
survivors(Blck, Att) :-
	(   trade(Blck, Att) ->
	    writeln("It's a trade, no survivors.")
	;   (	surviveBlock(Blck, Att) ->
		writeln("The blocker lives and the attacker dies.")
	    ;	writeln("The attacker lives and the blocker dies.")
	    )
	).

trade(Blck, Att) :-
	kill(Blck, Att),
	kill(Att, Blck).

surviveBlock(Blck, Att) :-
	\+ (trade(Blck, Att)),
        (   kill(Blck, Att)
	->  true
	;   false
	).
	   
kill(Cr1, Cr2) :-
	creature(Cr1, P1, _),
	creature(Cr2, _, T2),
	(   P1 >= T2
	->  true
	;   false
	).
	
saveQueries(FileName) :-
        protocol(FileName).

stopQueriesSaving :-
        noprotocol.

:- initialization set_prolog_flag(color_term, false).

/*If you are seeing multiple results delete eveything below this line but leave a single empty line directly below this one*/

creature(human,3,3).
creature(troll,3,3).
