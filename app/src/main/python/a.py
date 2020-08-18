
import numpy as np
import base64
from PIL import Image
import cv2
#from StringIO import StringIO
import numpy as np
import io
from io import BytesIO


def aa(bmp):
   # cvimg = readb64(bmp)

    d=base64.b64decode(bmp)

    np_data = np.fromstring(d,np.uint8)

    img = cv2.imdecode(np_data, cv2.IMREAD_UNCHANGED)


    g=cv2.cvtColor(img,cv2.COLOR_BGR2GRAY)




    nimg=cv2.adaptiveThreshold(g,255,cv2.ADAPTIVE_THRESH_GAUSSIAN_C,cv2.THRESH_BINARY,115,3)
    p=Image.fromarray(nimg)
    b=io.BytesIO()
    p.save(b,format="PNG")
    st=base64.b64encode(b.getvalue())
    return ""+str(st,'utf-8')
   # if(img):

    #img=cv2.imread(np1,cv2.IMREAD_UNCHANGED)



