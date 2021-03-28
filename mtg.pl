:- dynamic creature/3.

/*Remove ANSI escape codes from output*/
:- initialization set_prolog_flag(color_term, false).

survivors(Blck, Att) :-
	(   trade(Blck, Att) ->
	    writeln("It's a trade, no survivors.")
	;   (	surviveBlock(Blck, Att) ->
		writeln("The blocker lives and the attacker dies.")
	    ;	(   surviveBlock(Att, Blck) ->
		    writeln("The attacker lives and the blocker dies")
		;   false
		)
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

