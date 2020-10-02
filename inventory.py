#!/usr/bin/env python

import sys
import urllib, json

url = "http://localhost:8086/inventory/"

if len(sys.argv) == 2 and sys.argv[1] == '--list':
    response = urllib.urlopen(url+"list")
    print json.dumps(json.loads(response.read()))
elif len(sys.argv) == 3 and sys.argv[1] == '--host':
    response = urllib.urlopen(url+argv[2])
    print json.dumps(json.loads(response.read()))
else:
    sys.stderr.write("Need an argument, either --list or --host <host>\n")
