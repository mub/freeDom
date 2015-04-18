package com.github.mub.freeDom.parser;

import com.github.mub.freeDom.grammars.FreeDomBaseListener;
import com.github.mub.freeDom.grammars.FreeDomParser;
import com.github.mub.freeDom.model.Field;
import com.github.mub.freeDom.model.Fqn;
import com.github.mub.freeDom.model.FreeDomType;
import com.github.mub.freeDom.model.Struct;
import org.antlr.v4.runtime.misc.NotNull;
import org.slf4j.Logger;

import static com.google.common.base.Joiner.on;
import static java.lang.System.out;
import static org.slf4j.LoggerFactory.getLogger;

/**
 */
public class ModelParseListener extends FreeDomBaseListener {
    private static final Logger L = getLogger(ModelParseListener.class);

    private Field field;

    private Struct struct;

    private Fqn namespace;

    private FreeDomType type;

    /**
     * Parsing stage.
     */
    private static enum State {
        /**
         * Stage has not been set
         */
        NONE,
        /**
         * Model Header level
         */
        MODEL_HEADER,
        INCLUDE_DCL,
        ENUM_DCL,
        NAMESPACE_DCL,
        STRUCT_DCL,
        MAP_DCL,
        BITMAP_DCL,
        STRUCT_FIELD,
    }

    private State state = State.NONE;

    public ModelParseListener() {
        state = State.NONE;
    }

    @Override public void enterEnumDeclaration(@NotNull FreeDomParser.EnumDeclarationContext ctx) {
        out.printf("**** Enum d=%d, <<<%s>>>%n", ctx.depth(), ctx.qualifiedName().Identifier());
    }


    @Override public void enterEnumConstant(@NotNull FreeDomParser.EnumConstantContext ctx) {
        out.printf("**** Enum CONST d=%d, <<<%s>>>%n", ctx.depth(), ctx.Identifier());
    }

    @Override public void enterStructDeclaration(@NotNull FreeDomParser.StructDeclarationContext ctx) {
        out.printf("**** Struct d=%d, <<<%s>>>%n", ctx.depth(), ctx.qualifiedName().Identifier());
    }

    @Override public void enterFieldDeclaration(@NotNull FreeDomParser.FieldDeclarationContext ctx) {
        final FreeDomParser.FieldDimContext dimCtx = ctx.fieldDim();
        final FreeDomParser.FieldDefaultContext defCtx = ctx.fieldDefault();
        String signInfo = "";
        if (defCtx != null) {
            final FreeDomParser.SignPrefixContext signPrefix = defCtx.signPrefix();
            if (signPrefix != null) signInfo = signPrefix.getText();
        }
        final FreeDomParser.QualifiedNameContext fldFqn = ctx.qualifiedName();
        //final FreeDomParser.StdDataTypeContext stdCtx =  ctx.stdDataType();
        final String fldInfo = fldFqn == null ? "N/A" : fldFqn.Identifier().toString();
        out.printf("**** Field d=%d, >%s<>>>%s[%s]=%s%s<<<%n", ctx.depth(), ctx.reqOrOpt().FieldObligation(), fldInfo
            , dimCtx == null ? "N/A" : dimCtx.IntegerLiteral(), signInfo,
            defCtx == null ? "N/A" : defCtx.literal().toStringTree());
    }

    @Override public void enterFreeDomModel(@NotNull FreeDomParser.FreeDomModelContext ctx) {
        out.printf("FreeDom model =<<<%s>>>%n", ctx.toStringTree());
    }

    @Override public void enterNamespaceDeclaration(@NotNull FreeDomParser.NamespaceDeclarationContext ctx) {
        out.printf("Namespace d=%d, <<<%s>>>%n", ctx.depth(), ctx.qualifiedName().Identifier());
    }

    @Override public void enterQualifiedName(@NotNull FreeDomParser.QualifiedNameContext ctx) {
        out.printf("qName d=%d, <<<%s>>>%n", ctx.depth(), on(".").join(ctx.Identifier()));
    }

    @Override public void enterIncludeDeclaration(@NotNull FreeDomParser.IncludeDeclarationContext ctx) {
        out.printf("Include d=%d, <<<%s>>>%n", ctx.depth(), ctx.toStringTree());
    }

    @Override public void exitIncludeDeclaration(@NotNull FreeDomParser.IncludeDeclarationContext ctx) {
        out.printf("/include d=%d%n", ctx.depth());
    }

    @Override public void exitNamespaceDeclaration(@NotNull FreeDomParser.NamespaceDeclarationContext ctx) {
        out.printf("/namespace d=%d%n", ctx.depth());
    }

    @Override public void enterVersionDeclaration(@NotNull FreeDomParser.VersionDeclarationContext ctx) {

        out.printf("Version d=%d, \"%s\"%n", ctx.depth(), ctx.StringLiteral());
    }

    @Override public void exitVersionDeclaration(@NotNull FreeDomParser.VersionDeclarationContext ctx) {
        out.printf("/version d=%d%n", ctx.depth());
    }

}
