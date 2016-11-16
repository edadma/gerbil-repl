package xyz.hyperreal.gerbil_repl

import java.io.{File, PrintWriter, FileReader}

import jline.console.ConsoleReader

import xyz.hyperreal.gerbil._


object Main extends App {

	val reader = new ConsoleReader
	val out = new PrintWriter( reader.getTerminal.wrapOutIfNeeded(System.out), true )
	var line: String = null
	
	reader.setBellEnabled( false )
	reader.setPrompt( "> " )

	"""
	|Welcome to Gerbil version 0.1.
	|Type in expressions to have them evaluated.
	|Type help for more information.
	""".trim.stripMargin.lines foreach println
	println
	
	while ({line = reader.readLine; line != null}) {
		val com = line.trim split "\\s+" toList
			
		try {
			com match {
				case List( "help"|"h" ) =>
					"""
					|help (h)                         print this summary
					|quit (q)                         exit the REPL
					|trace (t) on/off                 turn execution trace on or off
					|stack (t) on/off                 turn exception stack trace on or off
					""".trim.stripMargin.lines foreach out.println
				case List( "quit"|"q" ) =>
					sys.exit
				case List( "trace"|"t", "on" ) =>
				case List( "trace"|"t", "off" ) =>
				case List( "stack"|"s", "on" ) =>
				case List( "stack"|"s", "off" ) =>
				case s :: _ if !s.head.isLetter => 
					val res = Gerbil.run( line )
					
					println( res, res.getClass )
				case Nil|List( "" ) =>
				case _ => out.println( "error interpreting command" )
			}
		}
		catch
		{
			case e: Exception =>
//					out.println( e )
				e.printStackTrace( out )
		}
			
		out.println
	}
	
}