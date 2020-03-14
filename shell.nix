with import <nixpkgs> {};

let
  sources = import ./nix/sources.nix;
  pkgs = import sources.nixpkgs { inherit system; };
  package-name = "ardumont.github.io";
in stdenv.mkDerivation rec {
  name = package-name;
  buildInputs = with pkgs; [
    clojure
    leiningen
  ];
  src = null;
}
