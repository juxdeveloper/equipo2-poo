#!/usr/bin/env bash
set -eu
cd -- "$(dirname -- "$0")"
trabajo=$(mktemp -d)
trap 'rm -rf -- "$trabajo"' EXIT
mkdir -p lib test/hospital
find src -name '*.java' | sort > "$trabajo/fuentes.txt"
javac --release 17 -encoding UTF-8 -d "$trabajo/base" @"$trabajo/fuentes.txt"
jar --create --file lib/atencion-medica.jar -C "$trabajo/base" hospital/interfaces/AtencionMedica.class
find src -name '*.java' ! -path '*/interfaces/*' | sort > "$trabajo/aplicacion.txt"
javac --release 17 -encoding UTF-8 -cp lib/atencion-medica.jar -sourcepath '' -d "$trabajo/app" @"$trabajo/aplicacion.txt"
cp -r "$trabajo/base/hospital/interfaces" "$trabajo/app/hospital/"
find test/hospital -name '*.class' -delete
cp -r "$trabajo/app/hospital" test/
jar --create --file lib/hospital-fase2.jar --main-class hospital.Main -C "$trabajo/app" hospital
javac --release 17 -encoding UTF-8 -cp test -d test test/PruebasHospital.java
java -cp test PruebasHospital
