from flask import Flask
from flask import render_template
from flask import request

defaults = { 'title': 'Opie Letters Search.html' ,
             'header': 'Amelia Alderson Opie Letters: Searching for Persons, Places, and Topics',
             'paragraph': 'Single word searches will reveal the most expansive results.'
}

app = Flask(__name__)
@app.route("/")
def get_my_index():
    header = get_request_value_with_fallback('header')
    values = {
        'title': defaults['title'],
        'header': header,
        'paragraph': defaults['paragraph'] 
    }
    return render_template("index-dyn-dict.html", dict=values)

@app.route("/resource/<resource_name>")
def get_local_resource(resource_name = ''):
    return render_template("resource.html", resource_name=resource_name)
    

@app.route("/resource/<resource_name>/value/<value>")
def get_local_resource_with_value(resource_name = '', value = ''):
    return render_template("resource1.html", resource_name=resource_name, value=value)

def get_request_value_with_fallback(key):
    if request.args.get(key):
        return request.args.get(key)
    return defaults[key]

if __name__ == '__main__':
    app.run(port=5000, debug=True)
