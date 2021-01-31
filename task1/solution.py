
"""
  author: Bobur Zakirov
  created: 24.12.2020
"""

import ply.lex as lex
import ply.yacc as yacc

tokens = [
    'LEFT',
    'NOT',
    'OR',
    'AND',
    'IMPL',
    'VAR',
    'RIGHT'
]

t_LEFT = r'\('
t_NOT = r'\!'
t_OR = r'\|'
t_AND = r'\&'
t_IMPL = r'[-][>]'
t_VAR = r'[A-Z0-9\'][A-Z0-9\']*'
t_RIGHT = r'\)'

t_ignore = ' \t\r'


def t_error(t):
    print("Illegal character '%s'" % t.value[0])
    t.lexer.skip(1)


lexer = lex.lex()


def p_expression(p):
    """
    expression : or
               | or IMPL expression
    """
    p[0] = p[1] if (len(p) == 2) else '(' + p[2] + ',' + p[1] + ',' + p[3] + ')'


def p_or(p):
    """
    or : and
       | or OR and
    """
    p[0] = p[1] if (len(p) == 2) else '(' + p[2] + ',' + p[1] + ',' + p[3] + ')'


def p_and(p):
    """
    and : not
        | and AND not
    """
    p[0] = p[1] if (len(p) == 2) else '(' + p[2] + ',' + p[1] + ',' + p[3] + ')'


def p_not(p):
    """
    not : NOT not
        | VAR
        | LEFT expression RIGHT
    """
    ln = len(p)
    p[0] = p[ln // 2] if (ln != 3) else '(' + p[1] + p[2] + ')'


parser = yacc.yacc()

_input = input()
print(parser.parse(_input))
