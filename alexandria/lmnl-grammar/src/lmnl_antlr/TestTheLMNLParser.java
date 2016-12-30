package lmnl_antlr;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Ronald Haentjens Dekker on 29/12/16.
 */
public class TestTheLMNLParser {

    @Test
    public void testParserTextWithOneRangeWithOneAnnotation() {
        String input = "[l [n}144{n]}He manages to keep the upper hand{l]";
        LMNLParser.DocumentContext documentContext = setupParserAndReturnParseTree(input);
        String stringTree = documentContext.toStringTree();
        assertEquals("([] ([14] ([18 14] [ l ([24 18 14] ([33 24 18 14] [ n }) 144 ([35 24 18 14] { n ])) }) He manages to keep the upper hand ([20 14] { l ])))", stringTree);
    }



    @Test
    public void testParserTextWithOneRange() {
        String input = "[l}He manages to keep the upper hand{l]";
        LMNLParser.DocumentContext documentContext = setupParserAndReturnParseTree(input);
        String stringTree = documentContext.toStringTree();
        assertEquals("([] ([14] ([18 14] [ l }) He manages to keep the upper hand ([20 14] { l ])))", stringTree);
    }

    @Test
    public void testParserTextOnly() {
        String input = "He manages to keep the upper hand";
        LMNLParser.DocumentContext documentContext = setupParserAndReturnParseTree(input);
        String stringTree = documentContext.toStringTree();
        assertEquals("([] He manages to keep the upper hand)", stringTree);
    }

    @Ignore
    @Test
    public void testParserComplexExample() {
        String input = "[excerpt\n" +
                "  [source [date}1915{][title}The Housekeeper{]]\n" +
                "  [author\n" +
                "    [name}Robert Frost{]\n" +
                "    [dates}1874-1963{]] }\n" +
                "[s}[l [n}144{n]}He manages to keep the upper hand{l]\n" +
                "[l [n}145{n]}On his own farm.{s] [s}He's boss.{s] [s}But as to hens:{l]\n" +
                "[l [n}146{n]}We fence our flowers in and the hens range.{l]{s]\n" +
                "{excerpt]";
        setupParserAndReturnParseTree(input);

    }

    private LMNLParser.DocumentContext setupParserAndReturnParseTree(String input) {
        LMNLLexer lexer = new LMNLLexer(new ANTLRInputStream(input));
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LMNLParser parser = new LMNLParser(tokens);
        parser.setBuildParseTree(true);
        // the method is called like the first rule in the parser grammar, in this case: document
        LMNLParser.DocumentContext document = parser.document();
        return document;
    }
}