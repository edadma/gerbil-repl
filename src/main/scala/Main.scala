package xyz.hyperreal.gerbilrepl

import java.io.{File, PrintWriter, FileReader}

import jline.console.ConsoleReader


object Main extends App {

	val reader = new ConsoleReader
	val out = new PrintWriter( reader.getTerminal.wrapOutIfNeeded(System.out), true )
	var line: String = null
	
	reader.setBellEnabled( false )
	reader.setPrompt( "> " )

	"""
	|Werlcome to Gerbil version 0.1.
	|Type in expressions to have them evaluated.
	|
	""".trim.stripMargin.lines foreach println

	while ({line = reader.readLine; line != null}) {
		val com = command.trim split "\\s+" toList
			
		try {
			com match {
				case List( "help"|"h" ) =>
					"""
					|help (h)                         print this summary
					|quit (q)                         exit the REPL
					|trace (t) on/off                 turn execution trace on or off
					""".trim.stripMargin.lines foreach out.println
				case List( "quit"|"q" ) =>
					sys.exit
				case List( "trace"|"t", "on" ) =>
				case List( "trace"|"t", "off" ) =>
				case s :: _ if !s.head.isLetter => 
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